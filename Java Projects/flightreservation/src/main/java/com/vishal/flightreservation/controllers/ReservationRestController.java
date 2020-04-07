package com.vishal.flightreservation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishal.flightreservation.dto.ReservationUpdateRequest;
import com.vishal.flightreservation.entities.Reservation;
import com.vishal.flightreservation.repos.ReservationRepository;

@RestController
public class ReservationRestController {

	@Autowired
	ReservationRepository reservationRepository;
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {	
		System.out.println("Reservatio id is : "+id);
		Reservation reservation = reservationRepository.getOne(id);
		System.out.println("Number Of Bags are : "+reservation.getNumberOfBags());
		System.out.println("Check In is  : "+reservation.getCheckedIn());
		System.out.println("Passenger Name : "+reservation.getPassenger().getFirstName());
		System.out.println("Email of Passenger is : "+reservation.getPassenger().getEmail());
		return reservation;
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		Reservation reservation = reservationRepository.getOne(request.getId());
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		return reservationRepository.save(reservation);
	}
}
