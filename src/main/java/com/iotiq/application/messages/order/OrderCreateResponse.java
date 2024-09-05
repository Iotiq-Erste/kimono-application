package com.iotiq.application.messages.order;

import java.util.UUID;

public record OrderCreateResponse(UUID newOrderId,
                                  String orderNumber) {

}
