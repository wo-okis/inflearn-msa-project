package com.wookis.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wookis.catalogservice.entity.CatalogEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog implements Serializable {

    private String productId;

    private String productName;

    private Integer stock;

    private Integer unitPrice;

    private String createdAt;

    public ResponseCatalog(CatalogEntity catalogEntity) {
        this.productId = catalogEntity.getProductId();
        this.productName = catalogEntity.getProductName();
        this.stock = catalogEntity.getStock();
        this.unitPrice = catalogEntity.getUnitPrice();
        this.createdAt = catalogEntity.getCreatedAt().toString();
    }

}
