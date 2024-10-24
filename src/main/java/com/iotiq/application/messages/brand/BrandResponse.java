package com.iotiq.application.messages.brand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BrandResponse {
    List<String> brands;
}
