package com.zplus.seckilldemo.service.impl;

import com.zplus.seckilldemo.DO.CommDO;
import com.zplus.seckilldemo.DO.CommStockDO;
import com.zplus.seckilldemo.DO.PromoDO;
import com.zplus.seckilldemo.dao.CommDOMapper;
import com.zplus.seckilldemo.dao.CommStockDOMapper;
import com.zplus.seckilldemo.error.BusinessException;
import com.zplus.seckilldemo.error.EmBusinessError;
import com.zplus.seckilldemo.service.CommService;
import com.zplus.seckilldemo.service.PromoService;
import com.zplus.seckilldemo.service.model.CommModel;
import com.zplus.seckilldemo.service.model.PromoModel;
import com.zplus.seckilldemo.utils.ModelConvertUtil;
import com.zplus.seckilldemo.validator.ValidatorImpl;
import com.zplus.seckilldemo.validator.ValidatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CommServiceImpl implements CommService
{
    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private CommDOMapper commDOMapper;

    @Autowired
    private CommStockDOMapper commStockDOMapper;

    @Autowired
    private PromoService promoService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public CommModel createComm(CommModel commModel) throws BusinessException
    {
        ValidatorResult result=validator.validate(commModel);
        if(result.isHasErrors())
        {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }

        //模型转换
        CommDO commDO= ModelConvertUtil.convertFromPojo(CommDO.class,commModel);

        //写入数据库
        commDOMapper.insertSelective(commDO);
        commModel.setId(commDO.getCommId());

        //写入秒杀
        PromoDO promoDO=new PromoDO();
        promoDO.setCommId(commModel.getId());
        promoDO.setPromoCommPrice(commModel.getPromoModel().getPromoCommPrice());
        promoDO.setStartDate(commModel.getPromoModel().getStartDate().toDate());
        promoDO.setEndDate(commModel.getPromoModel().getEndDate().toDate());
        promoService.createSeckillComm(promoDO);

        CommStockDO commStockDO=new CommStockDO();
        commStockDO.setCommId(commModel.getId());
        commStockDO.setCommStock(commModel.getCommStock());

        commStockDOMapper.insertSelective(commStockDO);

        // return this.getCommById(commModel.getId());
        return commModel;
    }

    @Override
    public List<CommModel> getCommList()
    {
        return commDOMapper.selectCommList();
    }

    // @Cacheable(value = "seckillComm")
    /**
     * 使用注解控制事务方法的优点:
     * 1:开发团队达成一致约定,明确标注事务方法的编程风格。
     * 2:保证事务方法的执行时间尽可能短,不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外部.
     * 3:不是所有的方法都需要事务,如只有一条修改操作,只读操作不需要事务控制.
     */
    //TODO 进行分离
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public CommModel getCommById(Integer id)
    {
        // this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(CommModel.class));
        //自定义datetime序列化和反序列化类
        //@JsonSerialize

        CommModel commModel= (CommModel) redisTemplate.opsForValue().get("com.zplus.seckilldemo.service.impl.CommServiceImplgetCommById"+id);

        if(commModel!=null)
        {
            return commModel;
        }

        CommDO commDO=commDOMapper.selectByPrimaryKey(id);
        if(commDO==null)
        {
            return null;
        }

        //获得库存数量
        CommStockDO commStockDO=commStockDOMapper.selectByCommId(id);

        //do->model
        commModel=ModelConvertUtil.convertFromPojo(CommModel.class,commDO);
        commModel.setId(commDO.getCommId());
        commModel.setCommPrice(commDO.getCommPrice());
        commModel.setCommStock(commStockDO.getCommStock());

        //获取秒杀活动商品信息
        PromoModel promoModel=promoService.getPromoByCommId(commModel.getId());
        if(promoModel!=null&&promoModel.getPromoStatus()!=3)
        {
            commModel.setPromoModel(promoModel);
        }

        redisTemplate.opsForValue().set("com.zplus.seckilldemo.service.impl.CommServiceImplgetCommById"+id,
                commModel,20L,TimeUnit.MINUTES);
        return commModel;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean decreaseStock(Integer commId, Integer quantityPurchased)
    {
        int affectedRow=commStockDOMapper.decreaseStock(commId,quantityPurchased);
        return affectedRow > 0;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void increaseSales(Integer commId, Integer quantityPurchased)
    {
        commDOMapper.increaseSales(commId,quantityPurchased);
    }
}
