package com.pablom.imatiaorders.controller;

import com.pablom.imatiaorders.controller.mapper.OrderTrackingDtoMapper;
import com.pablom.imatiaorders.openapi.controller.OrderApi;
import com.pablom.imatiaorders.openapi.dto.OrderTrackingDto;
import com.pablom.imatiaorders.service.OrderTrackingService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class OrderTrackingController implements OrderApi {
    private final OrderTrackingService orderTrackingService;
    private final OrderTrackingDtoMapper mapper;

    public ResponseEntity<OrderTrackingDto> saveOrderTracking(@ApiParam(value = "Ordertracking")
                                                              @Valid
                                                              @RequestBody final OrderTrackingDto orderTrackingDto) {
        return ResponseEntity.ok(mapper.asOrderTrackingDto(
                orderTrackingService.saveOrderTracking(mapper.asOrderTracking(orderTrackingDto))));

    }

}
