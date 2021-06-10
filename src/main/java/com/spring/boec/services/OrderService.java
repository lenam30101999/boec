package com.spring.boec.services;

import com.spring.boec.dtos.OrderDTO;
import com.spring.boec.entities.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class OrderService extends BaseService {

    public List<OrderDTO> getAllByCustomer(int customerId){
        List<Order> orders = orderRepository.findAllByCustomerId(customerId);
        orders.removeIf(p -> p.getPayment().isPaid());
        List<OrderDTO> orderDTOs = orders.stream().map(modelMapper::convertOrderDTO).collect(Collectors.toList());
        return orderDTOs;
    }
}
