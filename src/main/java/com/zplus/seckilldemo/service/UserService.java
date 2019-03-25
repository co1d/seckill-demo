package com.zplus.seckilldemo.service;

import com.zplus.seckilldemo.error.BusinessException;
import com.zplus.seckilldemo.service.model.UserModel;

public interface UserService
{
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    UserModel validateLogin(String telephone,String password) throws BusinessException;
}
