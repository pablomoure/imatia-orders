package com.pablom.imatiaorders.service;

import com.pablom.imatiaorders.service.model.OrderTracking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderTrackingServiceFacade implements OrderTrackingService {
    private final OrderTrackingEngineService orderTrackingEngineService;
    private final OrderTrackingMongoService orderTrackingMongoService;

    @Override
    public OrderTracking saveOrderTracking(final OrderTracking orderTracking) {
        boolean exist = orderTrackingMongoService.existOrderTracking(orderTracking);
        OrderTracking orderTrackingSaved = exist ? orderTrackingMongoService.getOrderTracking(orderTracking) : null;

        return orderTrackingMongoService.saveOrderTracking(
                orderTrackingEngineService.validateTransaction(orderTrackingSaved, orderTracking, exist));
    }
}
