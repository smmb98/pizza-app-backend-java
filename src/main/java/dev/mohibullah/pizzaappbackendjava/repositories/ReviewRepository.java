package dev.mohibullah.pizzaappbackendjava.repositories;

import dev.mohibullah.pizzaappbackendjava.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
