package com.vishal.flightreservation.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vishal.flightreservation.dto.ReservationRequest;
import com.vishal.flightreservation.entities.Flight;
import com.vishal.flightreservation.entities.Passenger;
import com.vishal.flightreservation.entities.Reservation;
import com.vishal.flightreservation.repos.FlightRepository;
import com.vishal.flightreservation.repos.PassengerRepository;
import com.vishal.flightreservation.repos.ReservationRepository;
import com.vishal.flightreservation.util.EmailUtil;
import com.vishal.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {

		Long flightId = request.getFlightId();
		Flight flight = flightRepository.getOne(flightId);
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation savedReservation = reservationRepository.save(reservation);
		
		String filePath = "/Users/Vishal/Documents/reservations/reservation" + savedReservation.getId() +".pdf";
		pdfGenerator.generateItinerary(savedReservation, filePath);
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		
		return savedReservation;
	}

}
