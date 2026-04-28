package sict.edu.mn.lab12.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sict.edu.mn.lab12.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
