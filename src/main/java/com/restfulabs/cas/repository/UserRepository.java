package com.restfulabs.cas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restfulabs.cas.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("Select u from com.restfulabs.cas.domain.User u where u.username =:username")
	Optional<User> findByUsername(@Param("username") String username);

	@Query("Select u from com.restfulabs.cas.domain.User u where u.username=?1 and u.password=?2")
	Optional<User> findByUsernameAndPassword(String username, String password);

}
