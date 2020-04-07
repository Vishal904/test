package com.vishal.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	//Reservation findOne(Long id);
	Reservation getOne(Long id);
}
