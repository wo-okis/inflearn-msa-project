package com.wookis.orderservice.repository;

import com.wookis.orderservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrderEntity, Long> {

    OrderEntity findByOrderId(String orderId);

    Iterable<OrderEntity> findByUserId(String userId);

}
