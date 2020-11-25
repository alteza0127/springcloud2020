package com.alteza.springcloud.controller;

import com.alteza.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Alteza
 * @DefaultProperties 默认同意 异常处理
 */
@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String OK(@PathVariable("id") Integer id) {
        String ok = paymentHystrixService.OK(id);
        return ok;
    }

    @GetMapping("/consumer/payment/hystrix/out/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
//    @HystrixCommand
    public String out(@PathVariable("id") Integer id) {
        String out = paymentHystrixService.out(id);
        return out;
    }

    public String paymentTimeOutFallbackMethod(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "***paymentInfo_Timeout***" + id + "\t" + "失败";
    }

    // 下面是全局fallback方法
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }

}
