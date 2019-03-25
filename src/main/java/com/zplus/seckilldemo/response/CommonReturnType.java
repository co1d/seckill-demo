package com.zplus.seckilldemo.response;

public class CommonReturnType
{
    //对应请求的返回处理结果 "success" "fail"
    private String status;

    //if status=success，data返回前端需要的json数据
    //status=fail，data返回通用的错误码格式
    private Object data;

    public static CommonReturnType create(Object result)
    {
        return CommonReturnType.create(result,"success");
    }

    public static CommonReturnType create(Object result, String status)
    {
        CommonReturnType type=new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
}
