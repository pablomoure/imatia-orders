package com.pablom.imatiaorders.service;

import com.pablom.imatiaorders.repository.entity.PreviousStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {OrderTrackingEngineService.class})
class OrderTrackingEngineServiceTest {

    @Autowired
    OrderTrackingEngineService engineService;

    @Test
    void shouldResultNONE() {
        //Given-When
        boolean result = engineService.isValidModification(PreviousStatus.NONE, 0);
        boolean result1 = engineService.isValidModification(PreviousStatus.NONE, 1);
        boolean result2 = engineService.isValidModification(PreviousStatus.NONE, 2);
        boolean result3 = engineService.isValidModification(PreviousStatus.NONE, 3);
        boolean result4 = engineService.isValidModification(PreviousStatus.NONE, 4);
        boolean result5 = engineService.isValidModification(PreviousStatus.NONE, 5);
        //Then
        Assertions.assertThat(result).isFalse();
        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isFalse();
        Assertions.assertThat(result3).isFalse();
        Assertions.assertThat(result4).isFalse();
        Assertions.assertThat(result5).isFalse();
    }

    @Test
    void shouldResultRECOGIDA_ALMACEN() {
        //Given-When
        boolean result = engineService.isValidModification(PreviousStatus.RECOGIDA_ALMACEN, 0);
        boolean result1 = engineService.isValidModification(PreviousStatus.RECOGIDA_ALMACEN, 1);
        boolean result2 = engineService.isValidModification(PreviousStatus.RECOGIDA_ALMACEN, 2);
        boolean result3 = engineService.isValidModification(PreviousStatus.RECOGIDA_ALMACEN, 3);
        boolean result4 = engineService.isValidModification(PreviousStatus.RECOGIDA_ALMACEN, 4);
        boolean result5 = engineService.isValidModification(PreviousStatus.RECOGIDA_ALMACEN, 5);
        //Then
        Assertions.assertThat(result).isFalse();
        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isTrue();
        Assertions.assertThat(result3).isTrue();
        Assertions.assertThat(result4).isTrue();
        Assertions.assertThat(result5).isFalse();
    }

    @Test
    void shouldResultEN_REPARTO() {
        //Given-When
        boolean result = engineService.isValidModification(PreviousStatus.EN_REPARTO, 0);
        boolean result1 = engineService.isValidModification(PreviousStatus.EN_REPARTO, 1);
        boolean result2 = engineService.isValidModification(PreviousStatus.EN_REPARTO, 2);
        boolean result3 = engineService.isValidModification(PreviousStatus.EN_REPARTO, 3);
        boolean result4 = engineService.isValidModification(PreviousStatus.EN_REPARTO, 4);
        boolean result5 = engineService.isValidModification(PreviousStatus.EN_REPARTO, 5);
        //Then
        Assertions.assertThat(result).isFalse();
        Assertions.assertThat(result1).isFalse();
        Assertions.assertThat(result2).isTrue();
        Assertions.assertThat(result3).isTrue();
        Assertions.assertThat(result4).isTrue();
        Assertions.assertThat(result5).isFalse();
    }

    @Test
    void shouldResultINCIDENCIA_EN_ENTREGA() {
        //Given-When
        boolean result = engineService.isValidModification(PreviousStatus.INCIDENCIA_EN_ENTREGA, 0);
        boolean result1 = engineService.isValidModification(PreviousStatus.INCIDENCIA_EN_ENTREGA, 1);
        boolean result2 = engineService.isValidModification(PreviousStatus.INCIDENCIA_EN_ENTREGA, 2);
        boolean result3 = engineService.isValidModification(PreviousStatus.INCIDENCIA_EN_ENTREGA, 3);
        boolean result4 = engineService.isValidModification(PreviousStatus.INCIDENCIA_EN_ENTREGA, 4);
        boolean result5 = engineService.isValidModification(PreviousStatus.INCIDENCIA_EN_ENTREGA, 5);
        //Then
        Assertions.assertThat(result).isFalse();
        Assertions.assertThat(result1).isFalse();
        Assertions.assertThat(result2).isTrue();
        Assertions.assertThat(result3).isTrue();
        Assertions.assertThat(result4).isTrue();
        Assertions.assertThat(result5).isFalse();
    }

    @Test
    void shouldResultENTREGADO() {
        //Given-When
        boolean result = engineService.isValidModification(PreviousStatus.ENTREGADO, 0);
        boolean result1 = engineService.isValidModification(PreviousStatus.ENTREGADO, 1);
        boolean result2 = engineService.isValidModification(PreviousStatus.ENTREGADO, 2);
        boolean result3 = engineService.isValidModification(PreviousStatus.ENTREGADO, 3);
        boolean result4 = engineService.isValidModification(PreviousStatus.ENTREGADO, 4);
        boolean result5 = engineService.isValidModification(PreviousStatus.ENTREGADO, 5);
        //Then
        Assertions.assertThat(result).isFalse();
        Assertions.assertThat(result1).isFalse();
        Assertions.assertThat(result2).isFalse();
        Assertions.assertThat(result3).isFalse();
        Assertions.assertThat(result4).isFalse();
        Assertions.assertThat(result5).isFalse();
    }
}