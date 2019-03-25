package com.zplus.seckilldemo.controller;

import com.alibaba.druid.util.StringUtils;
import com.zplus.seckilldemo.controller.VO.UserVO;
import com.zplus.seckilldemo.error.BusinessException;
import com.zplus.seckilldemo.error.EmBusinessError;
import com.zplus.seckilldemo.response.CommonReturnType;
import com.zplus.seckilldemo.service.UserService;
import com.zplus.seckilldemo.service.model.UserModel;
import com.zplus.seckilldemo.utils.ModelConvertUtil;
import com.zplus.seckilldemo.utils.PBKDF2Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

@RestController
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserController extends BasicController
{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /*@GetMapping("{id}")
    public ModelAndView getUser(@PathVariable("id") Integer id, Model model)
    {
        UserDO userDO=userDOMapper.selectByPrimaryKey(id);
        model.addAttribute("user", userDO.getName());
        model.addAttribute("title", "查看用户");
        return new ModelAndView("pages/view", "userModel", model);
    }*/

    @GetMapping("/{id}")
    public CommonReturnType getUser(@PathVariable("id") Integer id) throws BusinessException
    {
        //获取对应id对象并返回给前端
        UserModel userModel = userService.getUserById(id);

        //若获取的对应用户信息不存在
        if (userModel == null)
        {
            throw new BusinessException(EmBusinessError.USER_NOT_EXISTS);
        }

        //将核心领域模型用户对象转化为可供前端使用的VO
        UserVO userVO = convertFromUserModel(userModel);

        //返回通用对象
        return CommonReturnType.create(userVO);
    }

    @PostMapping("/login")
    public CommonReturnType login(String telephone,String password) throws BusinessException
    {
        if(org.apache.commons.lang3.StringUtils.isEmpty(telephone)||
                org.apache.commons.lang3.StringUtils.isEmpty(password))
        {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        UserModel userModel=userService.validateLogin(telephone,password);

        this.httpServletRequest.getSession().setAttribute("LOGIN_STATUS",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",
                ModelConvertUtil.convertFromPojo(UserVO.class,userModel));

        return CommonReturnType.create(null);
    }

    @PostMapping("/register")
    public CommonReturnType register(String telephone,String otpCode,String username,Byte gender,
                                     String password)
            throws BusinessException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        //验证短信验证码
        //验证码校验出错
        String sessionOtpCode= (String) this.httpServletRequest.getSession().getAttribute(telephone);

        if(!StringUtils.equals(otpCode,sessionOtpCode))
        {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码错误");
        }

        //用户注册
        UserModel userModel=new UserModel();
        userModel.setName(username);
        userModel.setGender(gender);
        userModel.setTelephone(telephone);
        userModel.setRegisterMode("phone");
        userModel.setEncryptSalt(PBKDF2Util.generateSalt());

        //数据库加盐值
        userModel.setEncryptPassword(PBKDF2Util.getEncryptedPassword(password,userModel.getEncryptSalt()));

        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    @PostMapping("/otp")
    public CommonReturnType getOtp(String telephone)
    {
        //生成验证码
        Random random=new Random();
        int randomInt=random.nextInt(90000);
        randomInt+=10000;
        String otpCode= String.valueOf(randomInt);

        //绑定手机号和验证码,后续使用redis
        httpServletRequest.getSession().setAttribute(telephone,otpCode);

        //将验证码发送给用户手机
        System.out.println(telephone+" "+otpCode);

        return CommonReturnType.create(null);
    }

    private UserVO convertFromUserModel(UserModel userModel)
    {
        if (userModel == null)
        {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

}
