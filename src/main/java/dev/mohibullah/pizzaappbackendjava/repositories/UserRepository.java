package dev.mohibullah.pizzaappbackendjava.repositories;

import dev.mohibullah.pizzaappbackendjava.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email); // Changed method name and parameter name

    Boolean existsByEmail(String email); // Changed method name and parameter name

    @Query("SELECT u FROM User u WHERE u.role = 'admin'")
    User findAdminUser();
}
