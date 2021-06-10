package com.spring.boec.services;

import com.spring.boec.dtos.PaymentDTO;
import com.spring.boec.entities.Payment;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Log4j2
@Service
@Transactional
public class PaymentService extends BaseService {

    public PaymentDTO checkoutPayment(int paymentId){
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        if (Objects.nonNull(payment) && !payment.isPaid()){
            payment.setPaid(true);
            paymentRepository.saveAndFlush(payment);
            return modelMapper.convertToPaymentDTO(payment);
        }
        return null;
    }
}
