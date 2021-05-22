package com.pablom.imatiaorders.service.mapper;

import com.pablom.imatiaorders.repository.entity.OrderTrackingEntity;
import com.pablom.imatiaorders.service.model.OrderTracking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderTrackingMapper {
    OrderTrackingEntity asOrderTrackingEntity(OrderTracking orderTracking);

    OrderTracking asOrderTracking(OrderTrackingEntity orderTrackingEntity);
}
