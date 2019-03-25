package com.zplus.seckilldemo.error;

public enum EmBusinessError implements CommonError
{
    //通用错误类型
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),

    USER_NOT_EXISTS(20001,"用户不存在"),
    USER_LOGIN_FAIL(20002,"手机号或密码不正确"),
    USER_NOT_LOGIN(20003,"用户未登录"),

    STOCK_NOT_ENOUGH(30001,"库存不足");

    private int errCode;
    private String errMsg;

    EmBusinessError(int errCode, String errMsg)
    {
        this.errCode=errCode;
        this.errMsg=errMsg;
    }

    @Override
    public int getErrCode()
    {
        return this.errCode;
    }

    @Override
    public String getErrMsg()
    {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String msg)
    {
        this.errMsg=msg;
        return this;
    }
}
