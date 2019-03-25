package com.zplus.seckilldemo.service;

import com.zplus.seckilldemo.error.BusinessException;
import com.zplus.seckilldemo.service.model.OrderModel;

public interface OrderService
{
    OrderModel createOrder(Integer userId, Integer commId, Integer promoId, Integer quantityPurchased) throws BusinessException;
}
