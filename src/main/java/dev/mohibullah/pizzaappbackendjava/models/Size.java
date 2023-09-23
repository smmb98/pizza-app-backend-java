package dev.mohibullah.pizzaappbackendjava.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Entity
public class Size extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String measurement;

    @Column
    private String description;

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Products_Sizes_Prices> productsSizesPrices = new ArrayList<Products_Sizes_Prices>();

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItem = new ArrayList<OrderItem>();
}
