package com.pablom.imatiaorders.service;

import com.pablom.imatiaorders.controller.dto.OrderTrackingDto;
import com.pablom.imatiaorders.repository.entity.PreviousStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {OrderTrackingServiceFacade.class})
class OrderTrackingServiceFacadeTest {

    @MockBean
    OrderTrackingEngineService orderTrackingEngineService;

    @MockBean
    OrderTrackingMongoService orderTrackingMongoService;

    @Autowired
    OrderTrackingServiceFacade orderTrackingServiceFacade;

    @Test
    void shouldManageOrderTracking() {
        //Given
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        OrderTrackingDto orderTrackingDto = new OrderTrackingDto()
                .orderId(new BigDecimal(1))
                .trackingStatusId(1)
                .changeStatusDate(offsetDateTime);

        List<OrderTrackingDto> orderTrackingDtoCollection = new ArrayList<>();
        orderTrackingDtoCollection.add(orderTrackingDto);

        //When
        when(orderTrackingMongoService.getPreviousState(orderTrackingDto.getOrderId())).thenAnswer(
                invocationOnMock -> {
                    BigDecimal id = invocationOnMock.getArgument(0);
                    Assertions.assertThat(id).isEqualTo(orderTrackingDto.getOrderId());
                    return PreviousStatus.NONE;
                }
        );

        when(orderTrackingEngineService.isValidModification(PreviousStatus.NONE, 1)).thenAnswer(
                invocationOnMock -> {
                    PreviousStatus previousStatus = invocationOnMock.getArgument(0);
                    Integer status = invocationOnMock.getArgument(1);
                    Assertions.assertThat(status).isEqualTo(orderTrackingDto.getTrackingStatusId());
                    Assertions.assertThat(previousStatus).isEqualTo(PreviousStatus.NONE);
                    return true;
                }
        );

        doNothing().when(orderTrackingMongoService).saveOrderTracking(any());

        orderTrackingServiceFacade.manageOrderTracking(orderTrackingDtoCollection);

        //Then
        then(orderTrackingMongoService).should(times(1)).getPreviousState(any());
        then(orderTrackingMongoService).should(times(1)).saveOrderTracking(any());
        then(orderTrackingEngineService).should(times(1))
                .isValidModification(PreviousStatus.NONE, 1);

    }


}