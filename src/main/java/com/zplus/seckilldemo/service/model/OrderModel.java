package com.zplus.seckilldemo.service.model;

import java.math.BigDecimal;

public class OrderModel
{
    //商品id
    private String id;

    private Integer userId;

    private Integer commId;

    //若promoId非空，则表示秒杀价格
    private BigDecimal commPrice;

    private Integer quantityPurchased;

    //若promoId非空，则表示秒杀价格
    private BigDecimal orderPrice;

    //若非空，则是以秒杀方式下单
    private Integer promoId;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getCommId()
    {
        return commId;
    }

    public void setCommId(Integer commId)
    {
        this.commId = commId;
    }

    public BigDecimal getCommPrice()
    {
        return commPrice;
    }

    public void setCommPrice(BigDecimal commPrice)
    {
        this.commPrice = commPrice;
    }

    public Integer getQuantityPurchased()
    {
        return quantityPurchased;
    }

    public void setQuantityPurchased(Integer quantityPurchased)
    {
        this.quantityPurchased = quantityPurchased;
    }

    public BigDecimal getOrderPrice()
    {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice)
    {
        this.orderPrice = orderPrice;
    }

    public Integer getPromoId()
    {
        return promoId;
    }

    public void setPromoId(Integer promoId)
    {
        this.promoId = promoId;
    }
}
