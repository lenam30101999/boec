package com.spring.boec.services;

import com.spring.boec.dtos.OrderDTO;
import com.spring.boec.entities.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Log4j2
@Service
@Transactional
public class OrderService extends BaseService {

    public OrderDTO getOrderByCustomer(int customerId){
        Order order = orderRepository.findTopByCustomerIdOrderByIdDesc(customerId);
        if (order != null && !order.getPayment().isPaid()){
            return modelMapper.convertOrderDTO(order);
        }
        return null;
    }

    public OrderDTO updateState(OrderDTO orderDTO){
        Order order = orderRepository.findById(orderDTO.getId());
        if (Objects.nonNull(order)){
            order.setState(orderDTO.getState());
            orderRepository.saveAndFlush(order);
            return modelMapper.convertOrderDTO(order);
        }
        return null;
    }
}
