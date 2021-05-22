package com.pablom.imatiaorders.exception;

import static java.lang.String.format;

public class OrderTrackingValidateException extends RuntimeException {
    public OrderTrackingValidateException(final String id) {
        super(format("OrderTracking with id %s doesn't validation", id));
    }
}
