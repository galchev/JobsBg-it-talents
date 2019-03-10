package com.example.demo;

import static org.junit.Assert.assertNotEquals;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dao.CompanyDAO;
import com.example.demo.dao.OfferDAO;
import com.example.demo.dto.OfferDTO;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class OfferTest {
	@Autowired
	protected CompanyDAO compDao;
	@Autowired
	protected OfferDAO offerDao;
	@Test
	public void addOffer() throws SQLException {
		int offersCount = offerDao.getAllOffers(null,null,null, null, null, null, null, null, null).size();
		compDao.addNewOffer(new OfferDTO(0, "Java devoper", 4500,null, 2, 1, 2, 3, 2, 65), 65);
		int offersCount2 = offerDao.getAllOffers(null,null,null, null, null, null, null, null, null).size();
		assertNotEquals(offersCount, offersCount2);
	}
	@Test
	public void deleteOffer() throws SQLException {
		int offersCount = offerDao.getAllOffers(null,null,null, null, null, null, null, null, null).size();
		compDao.deleteOffer((long) 29);
		int offersCount2 = offerDao.getAllOffers(null,null,null, null, null, null, null, null, null).size();
		assertNotEquals(offersCount, offersCount2);
	}

}
