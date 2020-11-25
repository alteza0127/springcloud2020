package com.alteza.springcloud.controller;

import com.alteza.springcloud.entities.CommonResult;
import com.alteza.springcloud.entities.Payment;
import com.alteza.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        logger.info("*****" + i);
        if (i > 0) {
            return new CommonResult(200, "插入数据成功" + serverPort, i);
        } else {
            return new CommonResult(444, "插入数据失败" + serverPort, null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment byId = paymentService.getPaymentById(id);
        logger.info("*****" + byId);
        if (null != byId) {
            return new CommonResult(200, "查询成功" + serverPort, byId);
        } else {
            return new CommonResult(444, "查询失败" + serverPort, null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String ele : services) {
            logger.info("***element:" + ele);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance in : instances) {
            logger.info(in.getServiceId() + "\t" + in.getHost() + "\t" + in.getPort() + "\t" + in.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("/payment/time")
    public String timeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
