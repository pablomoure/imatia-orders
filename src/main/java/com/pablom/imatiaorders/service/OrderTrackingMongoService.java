package com.pablom.imatiaorders.service;

import com.pablom.imatiaorders.controller.dto.OrderTrackingDto;
import com.pablom.imatiaorders.repository.OrderTrackingRepository;
import com.pablom.imatiaorders.repository.entity.PreviousStatus;
import com.pablom.imatiaorders.service.mapper.OrderTrackingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
class OrderTrackingMongoService {

    private final OrderTrackingRepository orderTrackingRepository;

    public PreviousStatus getPreviousState(final BigDecimal orderId) {
        return orderTrackingRepository.findById(orderId)
                .map(orderTrackingEntity -> PreviousStatus.getPreviousId(orderTrackingEntity.getTrackingStatusId()))
                .orElse(PreviousStatus.NONE);
    }

    public void saveOrderTracking(final OrderTrackingDto orderTracking) {
        orderTrackingRepository.save(OrderTrackingMapper.asOrderTrackingEntity(orderTracking));
    }

}
