package dev.mohibullah.pizzaappbackendjava.repositories;

import dev.mohibullah.pizzaappbackendjava.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
