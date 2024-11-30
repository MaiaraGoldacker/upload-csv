package com.upload.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.upload.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u JOIN FETCH u.roles where u.username = :username")
    Optional<User> findByUsernameFetchRoles(@Param("username") String username);
	
	Optional<User> findByUsername(String username);

}
