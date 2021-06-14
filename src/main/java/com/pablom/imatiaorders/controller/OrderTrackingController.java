package com.pablom.imatiaorders.controller;


import com.pablom.imatiaorders.controller.dto.OrderTrackingDto;
import com.pablom.imatiaorders.controller.dto.OrderTrackingResultDto;
import com.pablom.imatiaorders.controller.service.OrderApi;
import com.pablom.imatiaorders.service.OrderTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class OrderTrackingController implements OrderApi {
    private final OrderTrackingService orderTrackingService;

    @Override
    public ResponseEntity<List<OrderTrackingResultDto>> saveOrderTracking(
            final List<OrderTrackingDto> orderTrackingDto) {
        return ResponseEntity.ok(orderTrackingService.manageOrderTracking(orderTrackingDto));
    }
}

