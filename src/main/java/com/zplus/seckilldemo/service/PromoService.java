package com.zplus.seckilldemo.service;

import com.zplus.seckilldemo.DO.PromoDO;
import com.zplus.seckilldemo.service.model.PromoModel;

public interface PromoService
{
    PromoModel getPromoByCommId(Integer commId);

    void createSeckillComm(PromoDO promoDO);
}
