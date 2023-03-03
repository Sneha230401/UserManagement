package com.usermgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usermgmt.model.User;

@Repository
/**
 * @author PES1UG19CS490 Sneha P
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}
