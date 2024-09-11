package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SellerSalesTotal;
import com.devsuperior.dsmeta.repositories.projections.SellerSumTotalProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	public Page<SaleMinDTO> findSalesBySeller (String minDate, String maxDate, String name, Pageable p) {


		LocalDate parseMax = null;
		LocalDate parseMin = null;

		if (minDate.isBlank() || maxDate.isBlank()) {
			parseMax = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			parseMin = parseMax.minusYears(2);



		} else {

			parseMax = parseStringToLocalDate(maxDate);
			parseMin = parseStringToLocalDate(minDate);

		}

		Page<Sale> sales = repository.salesBySeller(parseMin, parseMax, name, p);
		return sales.map(x -> new SaleMinDTO(x));

	}

	public List<SellerSalesTotal> sumOfSalesBySeller (String minDate, String maxDate) {

		LocalDate parseMax = null;
		LocalDate parseMin = null;

		if (minDate.isBlank() || maxDate.isBlank()) {
			parseMax = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			parseMin = parseMax.minusYears(2);


		} else {

			parseMax = parseStringToLocalDate(maxDate);
			parseMin = parseStringToLocalDate(minDate);

		}

		List<SellerSumTotalProjection> sales = repository.salesSumBySeller(parseMin, parseMax);

		return sales.stream().map(x -> new SellerSalesTotal(x)).toList();

	}

	private LocalDate parseStringToLocalDate (String date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		return LocalDate.parse(date, formatter);

	}




}
