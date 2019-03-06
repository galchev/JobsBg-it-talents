package com.example.demo.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.OfferDAO;
import com.example.demo.dto.OfferDTO;
import com.example.demo.dto.UserDTO;

@RestController
public class OfferController {
	
	private List<OfferDTO> offerContainer = new LinkedList<>();
	@Autowired
	private OfferDAO offerDao;
	//za vremenno vytreshno polzvane
	@GetMapping("/offers")
	public List<OfferDTO> getAllUsers(){
		try {
			this.getOffersInList();
			return offerDao.getAllOffers();
		} catch (SQLException e) {
			e.printStackTrace();
			return new LinkedList<OfferDTO>();
		}
	}
	//za vremenno vytreshno polzvane
	public void getOffersInList() throws SQLException {
		this.offerContainer.addAll(offerDao.getAllOffers());
		System.out.println(this.offerContainer.size());
	}
	public List<OfferDTO> getOfferContainer() {
		return offerContainer;
	}
}
