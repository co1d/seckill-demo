package com.zplus.seckilldemo.service;

import com.zplus.seckilldemo.error.BusinessException;
import com.zplus.seckilldemo.service.model.CommModel;

import java.util.List;

public interface CommService
{
    //创建商品
    CommModel createComm(CommModel commModel) throws BusinessException;

    //商品列表浏览
    List<CommModel> getCommList();

    //商品详情浏览
    CommModel getCommById(Integer id);

    //库存扣减
    boolean decreaseStock(Integer commId,Integer quantityPurchased);

    //销量增加
    void increaseSales(Integer commId,Integer quantityPurchased);
}
