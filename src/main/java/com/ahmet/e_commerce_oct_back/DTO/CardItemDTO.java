package com.ahmet.e_commerce_oct_back.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class CardItemDTO {
    @NotEmpty(message = "Item quantity field should not be empty")
    private int quantity;
    @NotEmpty(message = "Cart creator ID field should not be empty")
    private Long userId;
    @NotEmpty(message = "Cart Product field should not be empty")
    private Long productId;
}
