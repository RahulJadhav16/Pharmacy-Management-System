package com.pms.doctor.service.Config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pms.doctor.service.Models.Doctor;
import com.pms.doctor.service.Repository.doctorRepository;
import org.springframework.security.core.userdetails.User;
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private doctorRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Doctor obj=repo.findByEmail(username);
		UserDetails Doctoruser=User.withUsername(obj.getEmail())
				.password(obj.getPassword())
				.roles(obj.getRole())
				.build();
		return Doctoruser;
	}

}
