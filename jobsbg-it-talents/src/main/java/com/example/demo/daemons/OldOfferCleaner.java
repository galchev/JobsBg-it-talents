package com.example.demo.daemons;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.example.demo.dto.OfferDTO;
import com.example.demo.model.Offer;



public class OldOfferCleaner extends Thread{
	private static final int OFFERS_DAYS_LIMIT = 30;
	private Set<Offer> offers;
	OldOfferCleaner(List<OfferDTO> offers) {
		//making the thread Deamon Thread with min priority
		this.setDaemon(true);
		this.setPriority(MIN_PRIORITY);
	}
	
	@Override
	public void run() {
		//the thread must work through the whole programm
		while(true) {
			//removing Offers, older than 30 days
			int currentDay = LocalDate.now().getDayOfYear();
			for(Iterator<Offer> it = this.offers.iterator(); it.hasNext();) {
				Offer o = (Offer) it.next();
				if(o.getDate().getDayOfYear() > currentDay - OFFERS_DAYS_LIMIT) {
					it.remove();
				}
			}
		}
	}
	
}
