package com.iotiq.application.messages.customer.measurement;

import java.util.List;

public record MeasurementInfo(List<BodyMeasurement> bodyMeasurements) {}
