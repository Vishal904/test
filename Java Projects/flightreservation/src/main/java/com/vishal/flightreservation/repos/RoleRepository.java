package com.vishal.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
