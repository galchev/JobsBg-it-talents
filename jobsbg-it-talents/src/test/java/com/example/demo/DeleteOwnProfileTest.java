package com.example.demo;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dao.UserDAO;
import com.example.demo.exceptions.NoSuchElementException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class DeleteOwnProfileTest {
	@Autowired UserDAO dao;
	@Test
	void deleteMyOwnProfileTest() throws SQLException, NoSuchElementException {
		dao.deleteProfile(77);
	}

}
