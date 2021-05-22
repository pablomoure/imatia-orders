package com.pablom.imatiaorders.service;

import com.pablom.imatiaorders.exception.OrderTrackingNotFoundException;
import com.pablom.imatiaorders.repository.OrderTrackingRepository;
import com.pablom.imatiaorders.service.mapper.OrderTrackingMapper;
import com.pablom.imatiaorders.service.model.OrderTracking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class OrderTrackingMongoService {
    private final OrderTrackingRepository orderTrackingRepository;
    private final OrderTrackingMapper mapper;

    public boolean existOrderTracking(final OrderTracking orderTracking) {
        return orderTrackingRepository.existsById(orderTracking.getOrderId());
    }

    public OrderTracking saveOrderTracking(final OrderTracking orderTracking) {
        return mapper.asOrderTracking(orderTrackingRepository.save(mapper.asOrderTrackingEntity(orderTracking)));
    }

    public OrderTracking getOrderTracking(final OrderTracking orderTracking) {
        return orderTrackingRepository.findById(orderTracking.getOrderId())
                .map(mapper::asOrderTracking)
                .orElseThrow(() -> new OrderTrackingNotFoundException(orderTracking.getOrderId().toString()));
    }
}
