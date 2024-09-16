package com.iotiq.application.messages.customer;

import java.util.UUID;

public record CustomerCreateResponse (UUID newCustomerId) {
}