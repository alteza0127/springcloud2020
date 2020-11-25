package com.alteza.springcloud.service;

import com.alteza.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Alteza
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
