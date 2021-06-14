package com.pablom.imatiaorders.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablom.imatiaorders.controller.dto.OrderTrackingDto;
import com.pablom.imatiaorders.controller.dto.OrderTrackingResultDto;
import com.pablom.imatiaorders.service.OrderTrackingServiceFacade;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

import static java.nio.charset.Charset.defaultCharset;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.StreamUtils.copyToString;

@ExtendWith(MockitoExtension.class)
class OrderTrackingControllerTest {

    @Mock
    private OrderTrackingServiceFacade orderTrackingServiceFacade;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new OrderTrackingController(orderTrackingServiceFacade))
                .setControllerAdvice(new GlobalErrorHandler())
                .build();
    }

    @Test
    @SneakyThrows
    void shouldReturnProjectAlertDefinition() {
        //Given
        String inputJson = OrderTrackingControllerTest.buildJson("input.json");

        List<OrderTrackingDto> list = io.vavr.collection.List.of(new OrderTrackingDto().orderId(new BigDecimal(1))
                .trackingStatusId(1)
                .changeStatusDate(Instant.now().atOffset(ZoneOffset.UTC))).toJavaList();

        List<OrderTrackingResultDto> result = io.vavr.collection.List.of(new OrderTrackingResultDto()
                .orderId(list.get(0).getOrderId())
                .isCorrect(true)).toJavaList();

        //When
        when(orderTrackingServiceFacade.manageOrderTracking(any())).thenAnswer(
                invocationOnMock -> result
        );

        //Then
        mockMvc.perform(post("/order/tracking/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    private static String buildJson(final String pathJson) {
        return new String(copyToString(new ClassPathResource(pathJson)
                .getInputStream(), defaultCharset()).getBytes());
    }

    @SneakyThrows
    private static List<OrderTrackingDto> mapperToObjectList(final String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.setDateFormat(df);
        ;
        return objectMapper.readValue(json, new TypeReference<List<OrderTrackingDto>>() {
        });
    }

}