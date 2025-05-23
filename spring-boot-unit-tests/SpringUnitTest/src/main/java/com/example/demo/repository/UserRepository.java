package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByName(String name);

	List<User> findByAgeBetween(int lowerAge, int upperAge);

	List<User> findByAgeBetweenAndGender(int lowerAge, int upperAge, String gender);
}