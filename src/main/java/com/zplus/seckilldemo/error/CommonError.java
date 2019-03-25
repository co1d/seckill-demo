package com.zplus.seckilldemo.error;

public interface CommonError
{
    int getErrCode();

    String getErrMsg();

    CommonError setErrMsg(String msg);
}
