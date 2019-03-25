package com.zplus.seckilldemo.controller;

import com.zplus.seckilldemo.controller.VO.UserVO;
import com.zplus.seckilldemo.error.BusinessException;
import com.zplus.seckilldemo.error.EmBusinessError;
import com.zplus.seckilldemo.response.CommonReturnType;
import com.zplus.seckilldemo.service.OrderService;
import com.zplus.seckilldemo.service.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class OrderController extends BasicController
{
    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping("/create")
    public CommonReturnType createOrder(Integer commId,Integer promoId, Integer quantityPurchased) throws BusinessException
    {
        Boolean loginStatus= (Boolean) httpServletRequest.getSession().getAttribute("LOGIN_STATUS");
        if(loginStatus==null|| !loginStatus)
        {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }

        UserVO userVO= (UserVO) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        OrderModel orderModel=orderService.createOrder(userVO.getId(),commId,promoId,quantityPurchased);

        return CommonReturnType.create(orderModel);
    }


    @PostMapping("/createTest")
    public CommonReturnType createOrderTest(@RequestBody Map<String,String> map) throws BusinessException
    {
        Integer promoId=5;
        Integer quantityPurchased=1;
        Integer commId=42;

        UserVO userVO= new UserVO();
        userVO.setTelephone(map.get("telephone"));
        userVO.setId(Integer.valueOf(map.get("id")));
        OrderModel orderModel=orderService.createOrder(userVO.getId(),commId,promoId,quantityPurchased);

        return CommonReturnType.create(orderModel);
    }

}