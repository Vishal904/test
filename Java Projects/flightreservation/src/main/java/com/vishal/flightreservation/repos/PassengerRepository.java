package com.vishal.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
