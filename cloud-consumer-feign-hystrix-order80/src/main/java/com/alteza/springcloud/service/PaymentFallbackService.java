package com.alteza.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author Alteza
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String OK(Integer id) {
        return "---PaymentFallbackService fallback OK---";
    }

    @Override
    public String out(Integer id) {
        return "---PaymentFallbackService fallback out---";
    }
}
