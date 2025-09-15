package com.wookis.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 120)
    private String productId;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false)
    private Integer unitPrice;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique = true)
    private String orderId;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault("CURRENT_TIMESTAMP" )
    private LocalDateTime createdAt;
}
