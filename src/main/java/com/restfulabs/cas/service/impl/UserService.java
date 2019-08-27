package com.restfulabs.cas.service.impl;

import com.restfulabs.cas.domain.Role;
import com.restfulabs.cas.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.restfulabs.cas.domain.User;
import com.restfulabs.cas.repository.UserRepository;
import com.restfulabs.cas.service.IUserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
		this.userRepository=userRepository;
		this.roleRepository=roleRepository;
		this.passwordEncoder=passwordEncoder;
	}

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Optional<Role> roles =roleRepository.findById(2L);
		HashSet<Role> role= new HashSet<>();
		role.add(roles.get());
		user.setRoles(role);
		return userRepository.save(user);
	}

}
