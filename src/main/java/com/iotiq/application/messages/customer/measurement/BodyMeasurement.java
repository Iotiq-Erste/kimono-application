package com.iotiq.application.messages.customer.measurement;

public record BodyMeasurement(
        int id,
        String name,
        String name_english,
        String measurement_unit,
        int id_size_group,
        String value
) {}
