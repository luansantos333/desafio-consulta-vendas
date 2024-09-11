package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.repositories.projections.SellerSumTotalProjection;

public class SellerSalesTotal {

    private String sellerName;
    private Double total;
    private Long id;


    public SellerSalesTotal(String sellerName, Double total, Long id) {
        this.sellerName = sellerName;
        this.total = total;
        this.id = id;
    }

    public SellerSalesTotal(SellerSumTotalProjection projection) {

        sellerName = projection.getName();
        total = projection.getTotal();
        id = projection.getId();

    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }

    public Long getId() {
        return id;
    }
}
