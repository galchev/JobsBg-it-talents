package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dao.UserDAO;
import com.example.demo.exceptions.AlreadyAppliedForThisOfferException;
import com.example.demo.exceptions.NotOfferFoundException;
import com.example.demo.exceptions.NotUserException;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class ApplicationTest {
	@Autowired UserDAO dao;
	@Test
	void applyForOfferTest() throws SQLException, NotOfferFoundException, AlreadyAppliedForThisOfferException, NotUserException {
		int applicationsSizebefore = dao.getApplications(76).size();
		dao.applyForOffer(10, 76);
		int applicationsSizeAfter = dao.getApplications(76).size();
		assertEquals(applicationsSizeAfter-1, applicationsSizebefore);
	}

}
