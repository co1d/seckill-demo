package com.zplus.seckilldemo.controller;

import com.zplus.seckilldemo.controller.VO.CommVO;
import com.zplus.seckilldemo.error.BusinessException;
import com.zplus.seckilldemo.response.CommonReturnType;
import com.zplus.seckilldemo.service.CommService;
import com.zplus.seckilldemo.service.model.CommModel;
import com.zplus.seckilldemo.service.model.PromoModel;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comm")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class CommController extends BasicController
{
    @Autowired
    private CommService commService;

    //创建商品
    @PostMapping("/create")
    public CommonReturnType createComm(CommModel commModel, @RequestParam("promoStartTime")String promoStartTime,
                                       @RequestParam("promoEndTime")String promoEndTime,
                                       @RequestParam("promoPrice")String promoPrice) throws BusinessException
    {
        PromoModel promoModel=new PromoModel();
        promoModel.setStartDate(DateTime.parse(promoStartTime, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm")));
        promoModel.setEndDate(DateTime.parse(promoEndTime, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm")));
        promoModel.setPromoCommPrice(new BigDecimal(promoPrice));
        promoModel.setPromoName(commModel.getCommName());
        commModel.setPromoModel(promoModel);

        CommModel commModelReturn=commService.createComm(commModel);

        // CommVO commVO= convertCommFormModel(commModelReturn);

        // return CommonReturnType.create(commVO);
        return CommonReturnType.create(null);
    }

    @GetMapping("/{id}")
    public CommonReturnType getComm(@PathVariable("id") Integer id)
    {
        CommModel commModel=commService.getCommById(id);

        CommVO commVO=convertCommFormModel(commModel);
        commVO.setNowTime(DateTime.now().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));

        return CommonReturnType.create(commVO);
    }

    @GetMapping("/time")
    public CommonReturnType getTimeNow()
    {
        return CommonReturnType.create(DateTime.now().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @GetMapping("/list")
    public CommonReturnType getCommList()
    {
        List<CommModel> commModelList=commService.getCommList();
        List<CommVO> commVOList = new ArrayList<>();

        for (CommModel commModel : commModelList)
        {
            commVOList.add(convertCommFormModel(commModel));
        }

        return CommonReturnType.create(commVOList);
    }

    private CommVO convertCommFormModel(CommModel commModel)
    {
        if(commModel==null)
        {
            return null;
        }

        CommVO commVO=new CommVO();
        BeanUtils.copyProperties(commModel,commVO);
        if(commModel.getPromoModel()!=null)
        {
            //有正在进行或即将进行的秒杀
            commVO.setPromoId(commModel.getPromoModel().getId());
            commVO.setPromoPrice(commModel.getPromoModel().getPromoCommPrice());
            commVO.setPromoStatus(commModel.getPromoModel().getPromoStatus());
            commVO.setStartDate(commModel.getPromoModel().getStartDate().
                    toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            commVO.setPromoEndTime(commModel.getPromoModel().getEndDate().
                    toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        }
        else
        {
            commVO.setPromoStatus(0);
        }
        return commVO;
    }
}
