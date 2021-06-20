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
public class FavoriteDTO {
    private int id;
    @JsonProperty("book")
    private BookDTO bookDTO;

    @JsonProperty("clothes")
    private ClothesDTO clothesDTO;

    @JsonProperty("electronic")
    private ElectronicDTO electronicDTO;

    @JsonProperty("customer_id")
    private int customerId;

}
