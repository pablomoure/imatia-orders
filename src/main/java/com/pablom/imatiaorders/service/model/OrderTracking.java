package com.pablom.imatiaorders.service.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class OrderTracking {
    private BigDecimal orderId;

    private Integer trackingStatusId;

    private OffsetDateTime changeStatusDate;
}
