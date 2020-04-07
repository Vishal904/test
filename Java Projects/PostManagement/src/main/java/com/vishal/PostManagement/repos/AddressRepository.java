package com.vishal.PostManagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.PostManagement.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	Address findByUserid(Long userid);

}
