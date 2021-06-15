package com.spring.boec.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderDTO {
    private int id;

    private LocalDateTime date;

    @JsonProperty("payment")
    private PaymentDTO paymentDTO;

    @JsonProperty("order_items")
    private List<OrderItemDTO> orderItems;

    @JsonProperty("total_item")
    private int totalItem;

    private String state;
}
