package com.alteza.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Alteza
 */
public interface PaymentService {

    public String PaymentInfoOK(Integer id);

    public String PaymentInfoTimeOut(Integer id);

    public String paymentCircuitBreaker(@PathVariable("id") Integer id);

}
