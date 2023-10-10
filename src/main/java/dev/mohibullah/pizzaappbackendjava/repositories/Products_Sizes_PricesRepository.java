package dev.mohibullah.pizzaappbackendjava.repositories;

import dev.mohibullah.pizzaappbackendjava.models.Products_Sizes_Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface Products_Sizes_PricesRepository extends JpaRepository<Products_Sizes_Prices, Integer> {
    @Modifying
    @Query(value = "DELETE FROM products_sizes_prices WHERE id = ?1", nativeQuery = true)
    void deleteByIdSql(int id);
}
