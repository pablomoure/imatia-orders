package com.pablom.imatiaorders.service;

import com.pablom.imatiaorders.controller.dto.OrderTrackingDto;
import com.pablom.imatiaorders.controller.dto.OrderTrackingResultDto;
import com.pablom.imatiaorders.repository.entity.PreviousStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderTrackingServiceFacade implements OrderTrackingService {

    private final OrderTrackingEngineService orderTrackingEngineService;
    private final OrderTrackingMongoService orderTrackingMongoService;


    @Override
    public List<OrderTrackingResultDto> manageOrderTracking(final List<OrderTrackingDto> orderTrackingDtoCollection) {
        return orderTrackingDtoCollection.stream()
                .map(this::processOrderTracking)
                .collect(Collectors.toList());

    }

    private OrderTrackingResultDto processOrderTracking(final OrderTrackingDto orderTrackingDto) {
        PreviousStatus previousStatus = orderTrackingMongoService.getPreviousState(orderTrackingDto.getOrderId());
        boolean isCorrect = orderTrackingEngineService.isValidModification(previousStatus,
                orderTrackingDto.getTrackingStatusId());

        if (isCorrect) {
            orderTrackingMongoService.saveOrderTracking(orderTrackingDto);
        }

        return new OrderTrackingResultDto()
                .orderId(orderTrackingDto.getOrderId())
                .isCorrect(isCorrect);
    }
}