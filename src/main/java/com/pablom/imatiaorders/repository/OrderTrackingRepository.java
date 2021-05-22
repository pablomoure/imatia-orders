package com.pablom.imatiaorders.repository;

import com.pablom.imatiaorders.repository.entity.OrderTrackingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface OrderTrackingRepository extends MongoRepository<OrderTrackingEntity, BigDecimal> {
}
