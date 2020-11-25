package com.alteza.springcloud.service.impl;

import com.alteza.springcloud.dao.PaymentDao;
import com.alteza.springcloud.entities.Payment;
import com.alteza.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Alteza
 */
@Service
public class PaymentServoceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

}
