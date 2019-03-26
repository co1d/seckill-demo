package com.zplus.seckilldemo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zplus.seckilldemo.controller.VO.LogResultVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect
{
    private final static Logger LOGGER= LoggerFactory.getLogger(LogAspect.class);

    //BasicController不需要执行切面
    @Pointcut("execution(public * com.zplus.seckilldemo.controller..*.*(..))"+
            "&&!execution(public * com.zplus.seckilldemo.controller.BasicController.*(..))")
    public void controllerMethod()
    {

    }

    @Before("controllerMethod()")
    public void logRequestInfo(JoinPoint joinPoint) throws JsonProcessingException
    {
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        StringBuilder requestLog=new StringBuilder();
        requestLog.append("请求信息：URL={").append(request.getRequestURL())
                .append("},\tHTTP_METHOD={").append(request.getMethod())
                .append("},\tIP={").append(request.getRemoteAddr())
                .append("},\tCLASS_METHOD={").append(joinPoint.getSignature().getDeclaringTypeName())
                .append(".").append(joinPoint.getSignature().getName()).append("},\t");

        if(joinPoint.getArgs().length==0)
        {
            requestLog.append("ARGS={}");
        }
        else
        {
            requestLog.append("ARGS=").append(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .writeValueAsString(joinPoint.getArgs()[0]));
        }

        LOGGER.info(requestLog.toString());
    }

    @AfterReturning(returning = "logResultVO",pointcut = "controllerMethod()")
    public void logResultVOInfo(LogResultVO logResultVO)
    {
        LOGGER.info("请求结果："+logResultVO.getCode()+"\t"+logResultVO.getMsg());
    }
}
