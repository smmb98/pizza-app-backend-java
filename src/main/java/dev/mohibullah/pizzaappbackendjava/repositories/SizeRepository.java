package dev.mohibullah.pizzaappbackendjava.repositories;

import dev.mohibullah.pizzaappbackendjava.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Integer> {
}
