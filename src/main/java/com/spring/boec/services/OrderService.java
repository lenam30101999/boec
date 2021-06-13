package com.spring.boec.services;

import com.spring.boec.dtos.OrderDTO;
import com.spring.boec.entities.Order;
import com.spring.boec.utils.Util;
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
            OrderDTO orderDTO = modelMapper.convertOrderDTO(order);
            orderDTO.setTotalItem(orderDTO.getOrderItems().size());
            return orderDTO;
        }
        return null;
    }

    public OrderDTO updateState(OrderDTO orderDTO){
        Order order = orderRepository.findById(orderDTO.getId());
        String state = getState(orderDTO.getState(),order.getState());
        if (Objects.nonNull(order) && state != null){
            order.setState(state);
            orderRepository.saveAndFlush(order);
            return modelMapper.convertOrderDTO(order);
        }
        return null;
    }

    public String getState(String orderDTO, String order) {
        if (orderDTO.equalsIgnoreCase(Util.REJECTED) && !order.equalsIgnoreCase(Util.RECEIVED)) {
            return Util.REJECTED;
        }
        if (Objects.isNull(order) && orderDTO.equalsIgnoreCase(Util.PENDING)) {
            return Util.PENDING;
        }
        if (Objects.nonNull(order)) {
            if (order.equalsIgnoreCase(Util.PENDING)) {
                if (orderDTO.equalsIgnoreCase(Util.APPROVED)) {
                    return Util.APPROVED;
                }
            } else if (order.equalsIgnoreCase(Util.APPROVED)) {
                if (orderDTO.equalsIgnoreCase(Util.RECEIVED)) {
                    return Util.RECEIVED;
                }
            }
        }
        return null;
    }
}
