package com.vishal.flightreservation.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vishal.flightreservation.entities.Flight;
import com.vishal.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {

	@Autowired
	FlightRepository flightRepos;
	
	@RequestMapping("/findFlights")
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String  to,
			@RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate, 
			ModelMap modelMap) {
		
		List<Flight> flights = flightRepos.findFlights(from,to,departureDate);	
		for(int i=0;i<flights.size();i++) {
			Flight flight = flights.get(i);
			System.out.println("Operating Airline is# : "+ flight.getOperatingAirlines()+"  getEstimate DepartureTime is : "+flight.getEstimatedDepartureTime());
		}
		modelMap.addAttribute("flights",flights);
		return "displayFlights";
	}
	
	@RequestMapping("admin/showAddFlight")
	public String showAddFlight() {
		return "addFlight";
	}
}
