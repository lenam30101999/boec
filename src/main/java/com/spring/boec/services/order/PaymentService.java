package com.spring.boec.services.order;

import com.spring.boec.dtos.PaymentDTO;
import com.spring.boec.model.order.Order;
import com.spring.boec.model.order.Payment;
import com.spring.boec.services.BaseService;
import com.spring.boec.utils.Util;
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
            Order order = payment.getOrder();
            payment.setPaid(true);
            order.setState(Util.RECEIVED);

            orderRepository.saveAndFlush(order);
            paymentRepository.saveAndFlush(payment);
            return modelMapper.convertToPaymentDTO(payment);
        }
        return null;
    }
}
