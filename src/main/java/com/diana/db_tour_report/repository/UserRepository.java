package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username
    Optional<User> findByName(String name);

    // Find user by username and password (for authentication)
    Optional<User> findByNameAndPassword(String name, String password);

    // Find user by username and role
    Optional<User> findByNameAndRole(String name, String role);

    // Find all users by role
    List<User> findByRole(String role);
}