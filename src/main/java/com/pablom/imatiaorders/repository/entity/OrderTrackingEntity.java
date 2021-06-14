package com.pablom.imatiaorders.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "OrderTrackingCollection")
public class OrderTrackingEntity {

    @Id
    private BigDecimal orderId;

    private Integer trackingStatusId;

    private Instant changeStatusDate;

    @LastModifiedDate
    private Instant savedDate;

    @Transient
    private PreviousStatus previousStatus;
}
