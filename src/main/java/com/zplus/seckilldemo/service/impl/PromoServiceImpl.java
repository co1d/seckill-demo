package com.zplus.seckilldemo.service.impl;

import com.zplus.seckilldemo.DO.PromoDO;
import com.zplus.seckilldemo.dao.PromoDOMapper;
import com.zplus.seckilldemo.service.PromoService;
import com.zplus.seckilldemo.service.model.PromoModel;
import com.zplus.seckilldemo.utils.ModelConvertUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoServiceImpl implements PromoService
{
    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByCommId(Integer commId)
    {
        PromoDO promoDO=promoDOMapper.selectByCommId(commId);

        PromoModel promoModel= ModelConvertUtil.convertFromPojo(PromoModel.class,promoDO);
        if(promoModel==null)
        {
            return null;
        }
        //date->datetime
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));

        //获取正在进行或即将进行的秒杀
        if(promoModel.getStartDate().isAfterNow())
        {
            promoModel.setPromoStatus(1);
        }
        else if(promoModel.getEndDate().isBeforeNow())
        {
            promoModel.setPromoStatus(3);
        }
        else
        {
            promoModel.setPromoStatus(2);
        }

        return promoModel;
    }

    @Override
    public void createSeckillComm(PromoDO promoDO)
    {
        promoDOMapper.insertSelective(promoDO);
    }
}
