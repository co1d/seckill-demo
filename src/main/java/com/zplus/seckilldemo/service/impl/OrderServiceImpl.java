package com.zplus.seckilldemo.service.impl;

import com.zplus.seckilldemo.DO.OrderDO;
import com.zplus.seckilldemo.DO.SequenceDO;
import com.zplus.seckilldemo.dao.OrderDOMapper;
import com.zplus.seckilldemo.dao.SequenceDOMapper;
import com.zplus.seckilldemo.error.BusinessException;
import com.zplus.seckilldemo.error.EmBusinessError;
import com.zplus.seckilldemo.service.CommService;
import com.zplus.seckilldemo.service.OrderService;
import com.zplus.seckilldemo.service.UserService;
import com.zplus.seckilldemo.service.model.CommModel;
import com.zplus.seckilldemo.service.model.OrderModel;
import com.zplus.seckilldemo.service.model.UserModel;
import com.zplus.seckilldemo.utils.ModelConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private CommService commService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderModel createOrder(Integer userId, Integer commId, Integer promoId, Integer quantityPurchased) throws BusinessException
    {
        //校验下单状态
        //商品校验
        CommModel commModel = commService.getCommById(commId);
        if (commModel == null)
        {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品不存在");
        }
        //用户校验
        UserModel userModel = userService.getUserById(userId);
        if (userModel == null)
        {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户不存在");
        }
        //购买数量校验
        if (quantityPurchased <= 0 || quantityPurchased > 99)
        {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "购买数量不正确");
        }
        //活动校验
        if (promoId != null)
        {
            if (!promoId.equals(commModel.getPromoModel().getId()))
            {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动信息不正确1");
            }
            else if (!commModel.getPromoModel().getPromoStatus().equals(2))
            {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动信息不正确");
            }
        }

        //订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setCommId(commId);
        orderModel.setUserId(userId);
        orderModel.setQuantityPurchased(quantityPurchased);
        if (promoId != null)
        {
            orderModel.setCommPrice(commModel.getPromoModel().getPromoCommPrice());
        }
        else
        {
            orderModel.setCommPrice(commModel.getCommPrice());
        }
        orderModel.setOrderPrice(orderModel.getCommPrice().multiply(BigDecimal.valueOf(quantityPurchased)));

        //生成交易流水号，订单号
        orderModel.setId(generateOrderId());
        OrderDO orderDO = ModelConvertUtil.convertFromPojo(OrderDO.class, orderModel);

        /*性能优化
        insert ignore
        将insert操作放在update前面
        insert可以并行执行，而update会导致行锁
        */
        int insertCount = orderDOMapper.insertSelective(orderDO);
        if (insertCount <= 0)
        {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "重复秒杀");
        }
        else
        {
            //落单减库存
            boolean decStockResult = commService.decreaseStock(commId, quantityPurchased);
            if (!decStockResult)
            {
                throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
            }
            else
            {
                //销量增加
                commService.increaseSales(commId, quantityPurchased);

                //返回前端
                return orderModel;
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateOrderId()
    {
        StringBuilder stringBuilder = new StringBuilder();
        //前8位为时间信息
        LocalDate localDate = LocalDate.now();
        stringBuilder.append(localDate.format(DateTimeFormatter.ISO_DATE).replace("-", ""));

        //中间6位为自增序列
        int sequence = 0;
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);

        stringBuilder.append(String.format("%06d", sequence));

        //最后2位为分库分表位,暂时写死
        stringBuilder.append("00");
        return stringBuilder.toString();
    }
}
