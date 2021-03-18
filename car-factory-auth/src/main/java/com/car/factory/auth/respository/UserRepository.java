package com.car.factory.auth.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.factory.auth.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User getUserByUsername(String username);
}
