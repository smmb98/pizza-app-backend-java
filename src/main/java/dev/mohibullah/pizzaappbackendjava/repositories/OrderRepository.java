package dev.mohibullah.pizzaappbackendjava.repositories;

import dev.mohibullah.pizzaappbackendjava.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
