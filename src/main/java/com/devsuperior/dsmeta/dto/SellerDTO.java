package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

public class SellerDTO {

    private String name;


    public SellerDTO(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public SellerDTO  (Seller s) {


        name = s.getName();

    }

}
