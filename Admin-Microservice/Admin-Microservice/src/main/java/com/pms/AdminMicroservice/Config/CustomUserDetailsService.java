package com.pms.AdminMicroservice.Config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pms.AdminMicroservice.Model.AdminDetails;
import com.pms.AdminMicroservice.Repository.AdminDetailsRepository;

import org.springframework.security.core.userdetails.User;
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AdminDetailsRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		AdminDetails obj=repo.findByEmail(username);
		UserDetails Adminuser=User.withUsername(obj.getEmail())
				.password(obj.getPassword())
				.roles(obj.getRole())
				.build();
		return Adminuser;
	}

}
