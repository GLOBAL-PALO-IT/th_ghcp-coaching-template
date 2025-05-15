package com.example.demo.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.User;

@DataJpaTest
public class UserRepositoryTests {

	private final UserRepository userRepository;

	@Autowired
	public UserRepositoryTests(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Test
	public void UserRepository_SaveAll_ReturnsSavedUser() {
		// Arrange
		User fakeUser = new User();
		fakeUser.setName("John Doe");
		fakeUser.setEmail("j@j.cc");

		// Act
		User savedFakedUser = userRepository.save(fakeUser);

		// Assert
		Assertions.assertThat(savedFakedUser).isNotNull();
		Assertions.assertThat(savedFakedUser.getId()).isGreaterThan(0);
		Assertions.assertThat(savedFakedUser.getName()).isEqualTo(fakeUser.getName());
	}

	@Test
	public void UserRepository_FindByAgeBetween_ReturnsUsersInRange() {
		// Arrange
		User user1 = new User();
		user1.setName("John");
		user1.setAge(25);

		User user2 = new User();
		user2.setName("Jane");
		user2.setAge(30);

		User user3 = new User();
		user3.setName("Bob");
		user3.setAge(35);

		userRepository.saveAll(List.of(user1, user2, user3));

		// Act
		List<User> usersInRange = userRepository.findByAgeBetween(27, 32);

		// Assert
		Assertions.assertThat(usersInRange).isNotNull();
		Assertions.assertThat(usersInRange).hasSize(1);
		Assertions.assertThat(usersInRange.get(0).getName()).isEqualTo("Jane");
		Assertions.assertThat(usersInRange.get(0).getAge()).isEqualTo(30);
	}

	@Test
	public void UserRepository_FindByAgeBetweenAndGender_ReturnsFilteredUsers() {
		// Arrange
		User user1 = new User();
		user1.setName("John");
		user1.setAge(25);
		user1.setGender("M");

		User user2 = new User();
		user2.setName("Jane");
		user2.setAge(30);
		user2.setGender("F");

		User user3 = new User();
		user3.setName("Bob");
		user3.setAge(35);
		user3.setGender("M");

		User user4 = new User();
		user4.setName("Alice");
		user4.setAge(28);
		user4.setGender("F");

		userRepository.saveAll(List.of(user1, user2, user3, user4));

		// Act
		List<User> femaleUsersInRange = userRepository.findByAgeBetweenAndGender(27, 32, "F");

		// Assert
		Assertions.assertThat(femaleUsersInRange).isNotNull();
		Assertions.assertThat(femaleUsersInRange).hasSize(2);
		Assertions.assertThat(femaleUsersInRange).extracting(User::getName).containsExactlyInAnyOrder("Jane", "Alice");
		Assertions.assertThat(femaleUsersInRange).extracting(User::getGender).containsOnly("F");
		Assertions.assertThat(femaleUsersInRange).extracting(User::getAge).allSatisfy(age -> {
			Assertions.assertThat(age).isBetween(27, 32);
		});
	}
}
