package com.pablom.imatiaorders.service.mapper;

import com.pablom.imatiaorders.controller.dto.OrderTrackingDto;
import com.pablom.imatiaorders.repository.entity.OrderTrackingEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderTrackingMapper {
    public static OrderTrackingEntity asOrderTrackingEntity(final OrderTrackingDto orderTracking) {
        return OrderTrackingEntity.builder()
                .orderId(orderTracking.getOrderId())
                .trackingStatusId(orderTracking.getTrackingStatusId())
                .changeStatusDate(orderTracking.getChangeStatusDate().toInstant())
                .savedDate(Instant.now())
                .build();
    }
}