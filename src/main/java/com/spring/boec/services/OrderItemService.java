package com.spring.boec.services;

import com.spring.boec.dtos.OrderItemDTO;
import com.spring.boec.entities.Book;
import com.spring.boec.entities.Clothes;
import com.spring.boec.entities.Electronic;
import com.spring.boec.entities.OrderItem;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Log4j2
public class OrderItemService extends BaseService {

    public OrderItemDTO addOrderItem(OrderItemDTO orderItemDTO){
        OrderItem orderItem = getOrderItem(orderItemDTO);
        if (orderItem != null){
            orderItem = orderItemRepository.save(orderItem);
            return modelMapper.convertOrderItemDTO(orderItem);
        }else return null;
    }

//    public OrderItemDTO updateOrderItem(OrderItemDTO orderItemDTO){
//
//    }

    private OrderItem getOrderItem(OrderItemDTO orderItemDTO){
        OrderItem orderItem;
        orderItem = getBook(orderItemDTO);
        if (orderItem == null){
            orderItem = getClothes(orderItemDTO);
        }
        if (orderItem == null){
            orderItem = getElectronic(orderItemDTO);
        }
        return orderItem;
    }

    private OrderItem getBook(OrderItemDTO orderItemDTO){
        if (orderItemDTO.getBookDTO().getId() != null){
            Book book = bookRepository.findById(orderItemDTO.getBookDTO().getId()).orElse(null);
            OrderItem saved = OrderItem.builder()
                    .item(book)
                    .quantity(orderItemDTO.getQuantity())
                    .build();
            return saved;
        }else return null;
    }

    private OrderItem getClothes(OrderItemDTO orderItemDTO){
        if (orderItemDTO.getBookDTO().getId() != null) {
            Clothes clothes = clothesRepository.findById(orderItemDTO.getBookDTO().getId()).orElse(null);
            OrderItem saved = OrderItem.builder()
                    .item(clothes)
                    .quantity(orderItemDTO.getQuantity())
                    .build();
            return saved;
        }else return null;
    }

    private OrderItem getElectronic(OrderItemDTO orderItemDTO){
        if (orderItemDTO.getBookDTO().getId() != null) {
            Electronic electronic = electronicRepository.findById(orderItemDTO.getBookDTO().getId()).orElse(null);
            OrderItem saved = OrderItem.builder()
                    .item(electronic)
                    .quantity(orderItemDTO.getQuantity())
                    .build();
            return saved;
        }else return null;
    }
}
