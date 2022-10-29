package com.ahmet.e_commerce_oct_back.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class CardItemDTO {

    private int quantity;

    private Long userId;

    private Long productId;
}
