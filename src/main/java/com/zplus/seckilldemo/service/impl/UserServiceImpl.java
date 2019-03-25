package com.zplus.seckilldemo.service.impl;

import com.zplus.seckilldemo.DO.UserDO;
import com.zplus.seckilldemo.DO.UserPasswordDO;
import com.zplus.seckilldemo.dao.UserDOMapper;
import com.zplus.seckilldemo.dao.UserPasswordDOMapper;
import com.zplus.seckilldemo.error.BusinessException;
import com.zplus.seckilldemo.error.EmBusinessError;
import com.zplus.seckilldemo.service.UserService;
import com.zplus.seckilldemo.service.model.UserModel;
import com.zplus.seckilldemo.utils.PBKDF2Util;
import com.zplus.seckilldemo.validator.ValidatorImpl;
import com.zplus.seckilldemo.validator.ValidatorResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id)
    {
        //获取用户对应的dataObject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if(userDO==null)
        {
            return null;
        }
        UserPasswordDO userPasswordDO=userPasswordDOMapper.selectByUserId(userDO.getId());

        return convertFromDataObject(userDO,userPasswordDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserModel userModel) throws BusinessException
    {
        if(userModel==null)
        {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ValidatorResult result= validator.validate(userModel);
        if(result.isHasErrors())
        {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }

        userModel.setId(null);
        UserDO userDO=convertFromUserModel(userModel);

        try
        {
            userDOMapper.insertSelective(userDO);
        }
        catch (DuplicateKeyException e)
        {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"手机号已存在");
        }

        userModel.setId(userDO.getId());

        UserPasswordDO userPasswordDO=convertPasswordFromUserModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
    }

    @Override
    public UserModel validateLogin(String telephone, String password) throws BusinessException
    {
        //通过用户手机号获取用户信息
        UserDO userDO=userDOMapper.selectByTelephone(telephone);
        if(userDO==null)
        {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }

        UserPasswordDO userPasswordDO=userPasswordDOMapper.selectByUserId(userDO.getId());

        UserModel userModel=convertFromDataObject(userDO,userPasswordDO);

        //用户信息验证
        try
        {
            if(!PBKDF2Util.authenticate(password,userModel.getEncryptPassword(),userModel.getEncryptSalt()))
            {
                throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL,"密码验证错误");
        }

        return userModel;
    }

    private UserDO convertFromUserModel(UserModel userModel)
    {
        if(userModel==null)
        {
            return null;
        }
        UserDO userDO=new UserDO();
        BeanUtils.copyProperties(userModel,userDO);

        return userDO;
    }

    private UserPasswordDO convertPasswordFromUserModel(UserModel userModel)
    {
        if(userModel==null)
        {
            return null;
        }
        UserPasswordDO userPasswordDO=new UserPasswordDO();
        userPasswordDO.setEncryptPassword(userModel.getEncryptPassword());
        userPasswordDO.setUserId(userModel.getId());
        userPasswordDO.setEncryptSalt(userModel.getEncryptSalt());
        return userPasswordDO;
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO)
    {
        if (userDO == null)
        {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);

        if (userPasswordDO != null)
        {
            userModel.setEncryptPassword(userPasswordDO.getEncryptPassword());
            userModel.setEncryptSalt(userPasswordDO.getEncryptSalt());
        }

        return userModel;
    }
}
