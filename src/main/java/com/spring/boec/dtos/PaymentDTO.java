package com.spring.boec.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PaymentDTO {
    private int id;

    @JsonProperty("total_money")
    private long totalMoney;

    @JsonProperty("is_paid")
    private boolean isPaid;
}
