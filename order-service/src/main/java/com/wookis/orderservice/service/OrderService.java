package com.wookis.orderservice.service;

import com.wookis.orderservice.dto.OrderDto;
import com.wookis.orderservice.entity.OrderEntity;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDetails);

    OrderDto getOrderByOrderId(String orderId);

    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
