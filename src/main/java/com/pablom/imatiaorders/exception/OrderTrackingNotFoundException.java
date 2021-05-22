package com.pablom.imatiaorders.exception;

import static java.lang.String.format;

public class OrderTrackingNotFoundException extends RuntimeException {
    public OrderTrackingNotFoundException(final String id) {
        super(format("OrderTracking with id %s doesn't not exist", id));
    }
}
