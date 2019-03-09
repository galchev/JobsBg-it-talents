package com.example.demo.daemons;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AdminDAO;
import com.example.demo.dto.OfferDTO;
import com.example.demo.dto.OfferToCleanDTO;
import com.example.demo.dto.SortedOfferDTO;
import com.example.demo.exceptions.NoSuchElementException;
import com.example.demo.model.Offer;

@Component
public class OldOfferCleaner implements Runnable{
	private static final int DAEMON_THREAD_CLEANER_TIME_TO_SLEEP = 5000;
	private static final String SELECT_ALL_OFFERS_QUERRY = "SELECT * FROM `jobs-bg`.offers";
	private static final int OFFERS_DAYS_LIMIT = 30;
	private static final String DELETE_OFFER_FROM_APPLICATIONS = "delete from applications where offer_id = ";
	private static final String DELETE_OFFER_FROM_OFFERS = "delete from offers where offer_id = ";
	private List<OfferToCleanDTO> offers = new LinkedList<>();
	private Statement statement;
	private Statement statement1;
	private Statement statement2;
	
	
	@Autowired
	private AdminDAO adminDao;
	
	
	public OldOfferCleaner() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.
				getConnection("jdbc:mysql://localhost:3306/jobs-bg", "root", "root-root1");
		statement = con.createStatement();
		statement1 = con.createStatement();
		statement2 = con.createStatement();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(DAEMON_THREAD_CLEANER_TIME_TO_SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("AZ SYM DAEMON NISHKATA I BACHKAM");
			
			ResultSet rs = null;
			try {
				rs = statement.executeQuery(SELECT_ALL_OFFERS_QUERRY + ";");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while (rs.next()) {
					OfferToCleanDTO offer = new OfferToCleanDTO(rs.getLong(1), rs.getDate(4));
					this.offers.add(offer);
					LocalDate offerDate = offer.getDate().toLocalDate();
					LocalDate dateToCompare = LocalDate.now().minusYears(8);
					if(offerDate.isBefore(dateToCompare)) {
						System.out.println(offerDate);
						System.out.println("iztrivam obqvata s nomer " + offer.getId());
						statement1.executeUpdate(DELETE_OFFER_FROM_APPLICATIONS  + offer.getId() + ";");
						statement2.executeUpdate(DELETE_OFFER_FROM_OFFERS  + offer.getId() + ";");
					}
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
}
