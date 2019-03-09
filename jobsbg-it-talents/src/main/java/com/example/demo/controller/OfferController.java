package com.example.demo.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.OfferDAO;
import com.example.demo.dto.OfferDTO;
import com.example.demo.dto.SortedOfferDTO;
import com.example.demo.dto.UserDTO;

@RestController
public class OfferController {
	
	private List<OfferDTO> offerContainer = new LinkedList<>();
	@Autowired
	private OfferDAO offerDao;
	@GetMapping("/offers")
	public List<SortedOfferDTO> getAllOffers(@RequestParam(name="sortBy",required = false) String sortBy,
			@RequestParam(name="cityId",required = false) Long cityId,
			@RequestParam(name="jobCategoryId",required = false) Long jobCategoryId,
			@RequestParam(name="jobTypeId",required = false) Long jobTypeId,
			@RequestParam(name="jobLevelId",required = false) Long jobLevelId,
			@RequestParam(name="jobLanguageId",required = false) Long jobLanguageId,
			@RequestParam(name="keyword",required = false) String keyword,
			@RequestParam(name="countryId",required = false) Long countryId,
			@RequestParam(name="companyId",required = false) Long companyId){
		try {
			return offerDao.getAllOffers(sortBy,cityId,countryId,jobCategoryId,jobTypeId,jobLevelId,jobLanguageId,keyword,companyId);
		} catch (SQLException e) {
			return new LinkedList<SortedOfferDTO>();
		}
	}
	//za vremenno vytreshno polzvane
//	public void getOffersInList() throws SQLException {
//		this.offerContainer.addAll(offerDao.getAllOffers());
//		System.out.println(this.offerContainer.size());
//	}
//	public List<OfferDTO> getOfferContainer() {
//		return offerContainer;
//	}
	
}
