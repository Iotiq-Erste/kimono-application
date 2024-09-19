package com.iotiq.application.messages.cartitem;

import com.iotiq.application.messages.product.ProductDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDetailDto {
    private ProductDto product;
    private Long quantity;
}
