package com.spring.boec.services;

import com.spring.boec.dtos.OrderItemDTO;
import com.spring.boec.entities.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
public class OrderItemService extends BaseService {

    public OrderItemDTO addOrderItem(OrderItemDTO orderItemDTO){
        Order order = orderRepository.findTopByCustomerIdOrderByIdDesc(orderItemDTO.getCustomerId());
        OrderItem orderItem;
        if (Objects.nonNull(order) && !order.getPayment().isPaid()){
            Map<String, Object> map = getOrderItem(orderItemDTO);
            orderItem = (OrderItem) map.get("object");
            long price = (long) map.get("price");
            if (orderItem != null){
                orderItem.setOrder(order);
                orderItem = orderItemRepository.save(orderItem);
                Payment payment = order.getPayment();
                payment.setTotalMoney(payment.getTotalMoney() + price * orderItemDTO.getQuantity());
                List<OrderItem> orderItems = order.getOrderItems();
                orderItems.add(orderItem);
                order.setPayment(payment);
                order.setOrderItems(orderItems);
                orderRepository.save(order);
            }
        }else {
            orderItem = initNewOrder(orderItemDTO);
        }

        if (Objects.nonNull(orderItem)){
            return modelMapper.convertOrderItemDTO(orderItem);
        }else return null;
    }

    public List<OrderItemDTO> updateOrderItem(List<OrderItemDTO> orderItemDTOs){
        int customerId = orderItemDTOs.get(0).getCustomerId();
        Order order = orderRepository.findTopByCustomerIdOrderByIdDesc(customerId);
        List<OrderItem> orderItems = order.getOrderItems();
        Payment payment = order.getPayment();

        if (!payment.isPaid()){
            for (OrderItemDTO orderItemDTO : orderItemDTOs){
                OrderItem updated = orderItemRepository.findById(orderItemDTO.getId()).orElse(null);
                if (Objects.nonNull(updated)){
                    updated.setQuantity(orderItemDTO.getQuantity());
                    orderItemRepository.saveAndFlush(updated);
                }
            }
            payment.setTotalMoney(orderItems.stream().mapToLong(p -> getPrice(p.getId()) * p.getQuantity()).sum());
            paymentRepository.saveAndFlush(payment);
            return orderItems.stream().map(modelMapper::convertOrderItemDTO).collect(Collectors.toList());
        }else return null;
    }

    public OrderItemDTO deleteOrderItem(int id){
        OrderItem orderItem = orderItemRepository.findById(id).orElse(null);
        if (Objects.nonNull(orderItem)){
            Order order = orderRepository.findById(orderItem.getOrder().getId());
            Payment payment = order.getPayment();
            long price = getPrice(id);
            payment.setTotalMoney(payment.getTotalMoney() - orderItem.getQuantity() * price);

            paymentRepository.saveAndFlush(payment);
            orderItemRepository.delete(orderItem);
            return modelMapper.convertOrderItemDTO(orderItem);
        }
        return null;
    }

    private OrderItem initNewOrder(OrderItemDTO orderItemDTO){
        Customer customer = customerRepository.findById(orderItemDTO.getCustomerId()).orElse(null);
        if (Objects.nonNull(customer)){
            Map<String, Object> map = getOrderItem(orderItemDTO);
            OrderItem orderItem = (OrderItem) map.get("object");
            long price = (long) map.get("price");
            Order order = new Order();
            Payment payment = new Payment();

            order.setDate(LocalDateTime.now());
            order.setCustomer(customer);
            order.setOrderItems(new ArrayList<>(Collections.singletonList(orderItem)));

            payment.setTotalMoney(price * orderItemDTO.getQuantity());
            payment.setCustomer(customer);
            payment.setPaid(false);

            order = orderRepository.save(order);
            payment.setOrder(order);
            orderItem.setOrder(order);

            orderItem = orderItemRepository.save(orderItem);
            paymentRepository.save(payment);
            return orderItem;
        }
        return null;
    }

    private Map<String, Object> getOrderItem(OrderItemDTO orderItemDTO){
        Map<String, Object> map;
        map = getBook(orderItemDTO);
        if (map == null){
            map = getClothes(orderItemDTO);
        }
        if (map == null){
            map = getElectronic(orderItemDTO);
        }
        return map;
    }

    private long getPrice(int orderItemId){
        OrderItem orderItem = orderItemRepository.findOrderItemById(orderItemId).orElse(null);
        if (Objects.nonNull(orderItem)){
            if (orderItem.getBook() != null) {
                return orderItem.getBook().getPrice();
            } else if (orderItem.getClothes() != null) {
                return orderItem.getClothes().getPrice();
            } else if (orderItem.getElectronic() != null) {
                return orderItem.getElectronic().getPrice();
            }
        }
        return 0;
    }

    private Map<String, Object> getBook(OrderItemDTO orderItemDTO){
        Map<String, Object> map = new HashMap<>();
        if (orderItemDTO.getBookDTO() != null &&
                orderItemDTO.getBookDTO().getId() != null){
            Book book = bookRepository.findById(orderItemDTO.getBookDTO().getId()).orElse(null);
            if (book != null && book.getStock() >= orderItemDTO.getQuantity()){
                OrderItem saved = OrderItem.builder()
                        .book(book)
                        .electronic(null)
                        .clothes(null)
                        .quantity(orderItemDTO.getQuantity())
                        .build();

                book.setStock(book.getStock() - orderItemDTO.getQuantity());
                bookRepository.saveAndFlush(book);
                map.put("object", saved);
                map.put("price", book.getPrice());
                return map;
            }
        }
        return null;
    }

    private Map<String, Object> getClothes(OrderItemDTO orderItemDTO){
        Map<String, Object> map = new HashMap<>();
        if (orderItemDTO.getClothesDTO() != null &&
                orderItemDTO.getClothesDTO().getId() != null) {
            Clothes clothes = clothesRepository.findById(orderItemDTO.getClothesDTO().getId()).orElse(null);
            if (clothes != null && clothes.getStock() >= orderItemDTO.getQuantity()){
                OrderItem saved = OrderItem.builder()
                        .clothes(clothes)
                        .book(null)
                        .clothes(null)
                        .quantity(orderItemDTO.getQuantity())
                        .build();

                clothes.setStock(clothes.getStock() - orderItemDTO.getQuantity());
                clothesRepository.saveAndFlush(clothes);
                map.put("object", saved);
                map.put("price", clothes.getPrice());
                return map;
            }
        }
        return null;
    }

    private Map<String, Object> getElectronic(OrderItemDTO orderItemDTO){
        Map<String, Object> map = new HashMap<>();
        if (orderItemDTO.getElectronicDTO() != null &&
                orderItemDTO.getElectronicDTO().getId() != null) {
            Electronic electronic = electronicRepository.findById(orderItemDTO.getElectronicDTO().getId()).orElse(null);
            if (electronic != null){
                OrderItem saved = OrderItem.builder()
                        .electronic(electronic)
                        .book(null)
                        .clothes(null)
                        .quantity(orderItemDTO.getQuantity())
                        .build();

                electronic.setStock(electronic.getStock() - orderItemDTO.getQuantity());
                electronicRepository.saveAndFlush(electronic);
                map.put("object", saved);
                map.put("price", electronic.getPrice());
                return map;
            }
        }
        return null;
    }
}
