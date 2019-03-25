package com.zplus.seckilldemo.controller.VO;

import java.math.BigDecimal;

public class CommVO
{
    private Integer id;

    private String commName;

    private BigDecimal commPrice;

    private Integer commStock;

    private String commDescription;

    private Integer commSales;

    private String commImgUrl;

    //商品是否在秒杀
    private Integer promoStatus;

    //秒杀活动价格
    private BigDecimal promoPrice;

    //秒杀商品ID
    private Integer promoId;

    //秒杀活动开始时间
    private String startDate;

    //活动结束时间
    private String promoEndTime;

    //获取服务器当前时间，用于前端做倒计时
    private String nowTime;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getCommName()
    {
        return commName;
    }

    public void setCommName(String commName)
    {
        this.commName = commName;
    }

    public BigDecimal getCommPrice()
    {
        return commPrice;
    }

    public void setCommPrice(BigDecimal commPrice)
    {
        this.commPrice = commPrice;
    }

    public Integer getCommStock()
    {
        return commStock;
    }

    public void setCommStock(Integer commStock)
    {
        this.commStock = commStock;
    }

    public String getCommDescription()
    {
        return commDescription;
    }

    public void setCommDescription(String commDescription)
    {
        this.commDescription = commDescription;
    }

    public Integer getCommSales()
    {
        return commSales;
    }

    public void setCommSales(Integer commSales)
    {
        this.commSales = commSales;
    }

    public String getCommImgUrl()
    {
        return commImgUrl;
    }

    public void setCommImgUrl(String commImgUrl)
    {
        this.commImgUrl = commImgUrl;
    }

    public Integer getPromoStatus()
    {
        return promoStatus;
    }

    public void setPromoStatus(Integer promoStatus)
    {
        this.promoStatus = promoStatus;
    }

    public BigDecimal getPromoPrice()
    {
        return promoPrice;
    }

    public void setPromoPrice(BigDecimal promoPrice)
    {
        this.promoPrice = promoPrice;
    }

    public Integer getPromoId()
    {
        return promoId;
    }

    public void setPromoId(Integer promoId)
    {
        this.promoId = promoId;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getNowTime()
    {
        return nowTime;
    }

    public void setNowTime(String nowTime)
    {
        this.nowTime = nowTime;
    }

    public String getPromoEndTime()
    {
        return promoEndTime;
    }

    public void setPromoEndTime(String promoEndTime)
    {
        this.promoEndTime = promoEndTime;
    }
}
