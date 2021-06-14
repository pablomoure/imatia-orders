package com.pablom.imatiaorders.service;



import com.pablom.imatiaorders.controller.dto.OrderTrackingDto;
import com.pablom.imatiaorders.controller.dto.OrderTrackingResultDto;

import java.util.List;

public interface OrderTrackingService {

    List<OrderTrackingResultDto> manageOrderTracking(final List<OrderTrackingDto> orderTrackingDtoCollection);
}
