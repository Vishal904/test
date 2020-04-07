package com.vishal.flightreservation.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vishal.flightreservation.entities.User;
import com.vishal.flightreservation.repos.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found for email " + username);
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.getRoles());
		
		/*
		//GrantedAuthority authority = new SimpleGrantedAuthority(user.getRoles());
		GrantedAuthority authority = (GrantedAuthority) user.getRoles();
        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getEmail(),
        		user.getPassword(), Arrays.asList(authority));

        return userDetails; */
	}

}
