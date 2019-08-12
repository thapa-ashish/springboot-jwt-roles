package com.restfulabs.cas.service;

import com.restfulabs.cas.domain.User;

public interface IUserService {

	public User findById(long id);

	public User findByEmail(String email);

	public User saveUser(User user);

}
