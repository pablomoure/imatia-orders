package com.pablom.imatiaorders.service;

import com.pablom.imatiaorders.exception.OrderTrackingValidateException;
import com.pablom.imatiaorders.service.model.OrderTracking;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
class OrderTrackingEngineService {
    public OrderTracking validateTransaction(final OrderTracking orderTrackingSaved,
                                             final OrderTracking orderTrackingNew,
                                             final boolean exist) {
        if (orderTrackingNew.getTrackingStatusId() == 1) {
            if (!exist) {
                return orderTrackingNew;
            }
            throw new OrderTrackingValidateException(orderTrackingNew.getOrderId().toString());
        } else if (IntStream.of(2, 3, 4).anyMatch(i -> orderTrackingNew.getTrackingStatusId() == i)) {
            if (!exist) {
                throw new OrderTrackingValidateException(orderTrackingNew.getOrderId().toString());
            } else if (orderTrackingSaved.getTrackingStatusId() == 4) {
                throw new OrderTrackingValidateException(orderTrackingNew.getOrderId().toString());
            }
            return orderTrackingNew;
        }
        throw new OrderTrackingValidateException(orderTrackingNew.getOrderId().toString());
    }
}
