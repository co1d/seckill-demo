package com.zplus.seckilldemo.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;

public class PromoModel
{
    private Integer id;

    //秒杀状态，0不参加 1未开始 2正在进行 3已结束
    private Integer promoStatus;

    private String promoName;

    private DateTime startDate;

    private DateTime endDate;
    private Integer commId;
    private BigDecimal promoCommPrice;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getPromoStatus()
    {
        return promoStatus;
    }

    public void setPromoStatus(Integer promoStatus)
    {
        this.promoStatus = promoStatus;
    }

    public String getPromoName()
    {
        return promoName;
    }

    public void setPromoName(String promoName)
    {
        this.promoName = promoName;
    }

    public DateTime getStartDate()
    {
        return startDate;
    }

    public void setStartDate(DateTime startDate)
    {
        this.startDate = startDate;
    }

    public DateTime getEndDate()
    {
        return endDate;
    }

    public void setEndDate(DateTime endDate)
    {
        this.endDate = endDate;
    }

    public Integer getCommId()
    {
        return commId;
    }

    public void setCommId(Integer commId)
    {
        this.commId = commId;
    }

    public BigDecimal getPromoCommPrice()
    {
        return promoCommPrice;
    }

    public void setPromoCommPrice(BigDecimal promoCommPrice)
    {
        this.promoCommPrice = promoCommPrice;
    }
}
