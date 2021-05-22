package com.pablom.imatiaorders.controller.mapper;

import com.pablom.imatiaorders.openapi.dto.OrderTrackingDto;
import com.pablom.imatiaorders.service.model.OrderTracking;
import org.mapstruct.Mapper;

@Mapper
public interface OrderTrackingDtoMapper {
    OrderTrackingDto asOrderTrackingDto(OrderTracking orderTracking);
    OrderTracking asOrderTracking(OrderTrackingDto orderTrackingDto);
}
