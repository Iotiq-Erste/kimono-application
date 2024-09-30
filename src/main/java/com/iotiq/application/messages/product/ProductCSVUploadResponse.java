package com.iotiq.application.messages.product;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductCSVUploadResponse {

    List<String> messages = new ArrayList<>();
}
