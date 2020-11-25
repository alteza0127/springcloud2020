package com.alteza.springcloud.controller;

import com.alteza.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Alteza
 */
@RestController
public class PaymentController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String OK(@PathVariable("id") Integer id) {
        String ok = paymentService.PaymentInfoOK(id);
        logger.info("***result***" + ok);
        return ok;
    }

    @GetMapping("/payment/hystrix/out/{id}")
    public String out(@PathVariable("id") Integer id) {
        String ok = paymentService.PaymentInfoTimeOut(id);
        logger.info("***result***" + ok);
        return ok;
    }

    //====服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        logger.info("****result: " + result);
        return result;
    }

}
