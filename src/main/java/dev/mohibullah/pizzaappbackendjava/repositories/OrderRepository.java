package dev.mohibullah.pizzaappbackendjava.repositories;

import dev.mohibullah.pizzaappbackendjava.enums.Stage;
import dev.mohibullah.pizzaappbackendjava.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByOrderStageIn(List<Stage> orderStages, Sort sort);

    Page<Order> findByOrderStageIn(List<Stage> orderStages, Pageable pageable);

}
