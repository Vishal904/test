package com.vishal.flightreservation.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vishal.flightreservation.dto.ReservationRequest;
import com.vishal.flightreservation.entities.Flight;
import com.vishal.flightreservation.entities.Reservation;
import com.vishal.flightreservation.repos.FlightRepository;
import com.vishal.flightreservation.services.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		Flight flight = flightRepository.getOne(flightId);
		modelMap.addAttribute("flight",flight);
		return "completeReservation";
	}
	
	@RequestMapping(value="/completeReservation", method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {
		
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg", "reservation created successfully and the id is "+reservation.getId());
		return "reservationConfirmation";
	}
}
