package com.wookis.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wookis.orderservice.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOrder {

    private String productId;

    private Integer qty;

    private Integer unitPrice;

    private Integer totalPrice;

    private String createdAt;

    private String orderId;

    public ResponseOrder(OrderEntity orderEntity) {
        this.productId = orderEntity.getProductId();
        this.qty = orderEntity.getQty();
        this.unitPrice = orderEntity.getUnitPrice();
        this.totalPrice = orderEntity.getTotalPrice();
        this.createdAt = orderEntity.getCreatedAt().toString();
        this.orderId = orderEntity.getOrderId();
    }
}
