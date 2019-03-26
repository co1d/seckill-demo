package com.zplus.seckilldemo.controller.VO;

public class LogResultVO<T>
{
    private Integer code;
    private String msg;
    private T data;

    private LogResultVO(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    private LogResultVO(Integer code, String msg, T data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> LogResultVO getSuccess(String msg) {
        return new LogResultVO(1, msg);
    }

    public static <T> LogResultVO getSuccess(String msg, T data) {
        return new LogResultVO(1, msg, data);
    }

    public static <T> LogResultVO getFailed(String msg) {
        return new LogResultVO(0, msg);
    }

    public static <T> LogResultVO getFailed(String msg, T data) {
        return new LogResultVO(0, msg, data);
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }
}
