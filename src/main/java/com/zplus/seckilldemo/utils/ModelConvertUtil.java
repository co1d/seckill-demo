package com.zplus.seckilldemo.utils;

import org.springframework.beans.BeanUtils;

public class ModelConvertUtil
{
    public static <T> T convertFromPojo(Class<T> clazz,Object object)
    {
        if(object==null)
        {
            return null;
        }

        try
        {
            T target=clazz.newInstance();
            BeanUtils.copyProperties(object,target);
            return target;

        } catch (InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
