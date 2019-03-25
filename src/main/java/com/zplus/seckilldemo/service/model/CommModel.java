package com.zplus.seckilldemo.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CommModel
{
    private Integer id;

    @NotBlank(message = "商品名称不能为空")
    private String commName;

    @NotNull(message = "商品价格不能为空")
    @Min(value = 0,message = "商品价格必须大于0")
    private BigDecimal commPrice;

    @NotNull(message = "库存不能为空")
    private Integer commStock;

    @NotBlank(message = "商品描述信息不能为空")
    private String commDescription;

    private Integer commSales;

    @NotBlank(message = "商品图片不能为空")
    private String commImgUrl;

    //聚合模型，如果promoModel不为空，则表示还拥有未结束的秒杀活动
    private PromoModel promoModel;

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

    public PromoModel getPromoModel()
    {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel)
    {
        this.promoModel = promoModel;
    }
}
