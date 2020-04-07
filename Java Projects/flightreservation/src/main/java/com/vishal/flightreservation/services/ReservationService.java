package com.vishal.flightreservation.services;

import com.vishal.flightreservation.dto.ReservationRequest;
import com.vishal.flightreservation.entities.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest request);
}
