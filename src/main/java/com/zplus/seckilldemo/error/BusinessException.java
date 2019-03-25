package com.zplus.seckilldemo.error;

public class BusinessException extends Exception implements CommonError
{
    private CommonError commonError;

    //直接接收EmBusinessError的传参用于构造业务异常
    public BusinessException(CommonError commonError)
    {
        super();
        this.commonError = commonError;
    }

    //接收自定义消息
    public BusinessException(CommonError commonError, String message)
    {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(message);
    }

    @Override
    public int getErrCode()
    {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg()
    {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String msg)
    {
        this.commonError.setErrMsg(msg);
        return this;
    }
}
