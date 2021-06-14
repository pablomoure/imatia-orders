package com.pablom.imatiaorders.service;

import com.pablom.imatiaorders.controller.dto.OrderTrackingDto;
import com.pablom.imatiaorders.repository.OrderTrackingRepository;
import com.pablom.imatiaorders.repository.entity.OrderTrackingEntity;
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
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {OrderTrackingMongoService.class})
class OrderTrackingMongoServiceTest {

    @MockBean
    OrderTrackingRepository orderTrackingRepository;

    @Autowired
    OrderTrackingMongoService mongoService;


    @Test
    void shouldSaveOrderTrackingEntity() {
        //Given
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        OrderTrackingDto orderTrackingDto = new OrderTrackingDto().orderId(new BigDecimal(1))
                .trackingStatusId(1)
                .changeStatusDate(offsetDateTime);

        OrderTrackingEntity orderTrackingEntity = OrderTrackingEntity.builder()
                .orderId(new BigDecimal(1))
                .trackingStatusId(1)
                .changeStatusDate(offsetDateTime.toInstant().truncatedTo(ChronoUnit.SECONDS))
                .savedDate(offsetDateTime.toInstant().truncatedTo(ChronoUnit.SECONDS))
                .build();

        //When
        when(orderTrackingRepository.save(any())).thenAnswer(invocationOnMock -> {
            OrderTrackingEntity entity = invocationOnMock.getArgument(0);
            entity.setSavedDate(entity.getSavedDate().truncatedTo(ChronoUnit.SECONDS));
            entity.setChangeStatusDate(entity.getChangeStatusDate().truncatedTo(ChronoUnit.SECONDS));

            assertThat(entity.toString()).hasToString(orderTrackingEntity.toString());
            return orderTrackingEntity;
        });

        mongoService.saveOrderTracking(orderTrackingDto);

        //Then
        then(orderTrackingRepository).should(times(1)).save(orderTrackingEntity);
    }

    @Test
    void shouldReturnOrderTrackingEntity() {
        //Given
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        OrderTrackingDto orderTrackingDto = new OrderTrackingDto().orderId(new BigDecimal(1))
                .trackingStatusId(1)
                .changeStatusDate(offsetDateTime);

        OrderTrackingEntity orderTrackingEntity = OrderTrackingEntity.builder()
                .orderId(new BigDecimal(1))
                .trackingStatusId(1)
                .changeStatusDate(offsetDateTime.toInstant().truncatedTo(ChronoUnit.SECONDS))
                .savedDate(offsetDateTime.toInstant().truncatedTo(ChronoUnit.SECONDS))
                .build();

        //When
        when(orderTrackingRepository.findById(any())).thenAnswer(invocationOnMock -> {
            BigDecimal id = invocationOnMock.getArgument(0);
            Assertions.assertThat(id).isEqualTo(orderTrackingDto.getOrderId());
            return Optional.ofNullable(orderTrackingEntity);
        });

        PreviousStatus result = mongoService.getPreviousState(orderTrackingDto.getOrderId());

        //Then
        Assertions.assertThat(result).isEqualTo(PreviousStatus.RECOGIDA_ALMACEN);
        then(orderTrackingRepository).should(times(1)).findById(orderTrackingDto.getOrderId());

    }

}