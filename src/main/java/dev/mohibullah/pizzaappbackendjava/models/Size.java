package dev.mohibullah.pizzaappbackendjava.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private List<Products_Sizes_Prices> productsSizesPrices = new ArrayList<>();

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItem = new ArrayList<>();
}
