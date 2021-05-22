package com.pablom.imatiaorders.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Document(collection = "OrderTrackingCollection")
public class OrderTrackingEntity {
    @Id
    private BigDecimal orderId;

    private Integer trackingStatusId;

    private OffsetDateTime changeStatusDate;
}
