package dev.mohibullah.pizzaappbackendjava.repositories;

import dev.mohibullah.pizzaappbackendjava.models.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToppingRepository extends JpaRepository<Topping, Integer> {
}
