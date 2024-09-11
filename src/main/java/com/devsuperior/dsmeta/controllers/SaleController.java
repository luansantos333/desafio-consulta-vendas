package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SellerSalesTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {


    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/report")
    public ResponseEntity<Page<SaleMinDTO>>
    getReport(@RequestParam(name = "minDate", defaultValue = "", required = false) String minDate,
              @RequestParam(name = "maxDate", defaultValue = "", required = false) String maxDate,
              @RequestParam(name = "name", defaultValue = "", required = false) String name,
              Pageable p) {

        return ResponseEntity.ok(service.findSalesBySeller(minDate, maxDate, name, p));
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<List<SellerSalesTotal>> getSummary(@RequestParam (name = "minDate", defaultValue =  "", required = false) String minDate, @RequestParam (name = "maxDate", defaultValue =  "", required = false) String maxDate) {
        return ResponseEntity.ok(service.sumOfSalesBySeller(minDate, maxDate));
    }
}
