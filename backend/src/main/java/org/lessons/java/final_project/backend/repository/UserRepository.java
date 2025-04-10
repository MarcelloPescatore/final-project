package org.lessons.java.final_project.backend.repository;

import org.lessons.java.final_project.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;



public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
}
