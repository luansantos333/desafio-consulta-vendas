package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.repositories.projections.SellerSumTotalProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT obj FROM Sale obj JOIN FETCH obj.seller WHERE obj.date BETWEEN :minDate AND :maxDate AND obj.seller.name LIKE CONCAT('%', :name, '%')",
            countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller")
     Page<Sale> salesBySeller(LocalDate minDate, LocalDate maxDate, String name, Pageable p);

    @Query( nativeQuery = true, value = "SELECT seller.name, seller.id, SUM(sales.amount) AS total FROM tb_sales AS sales INNER JOIN tb_seller AS seller ON sales.seller_id = seller.id WHERE sales.date BETWEEN :minDate AND :maxDate GROUP BY seller.id, seller.name")
    List<SellerSumTotalProjection> salesSumBySeller(LocalDate minDate, LocalDate maxDate);


}
