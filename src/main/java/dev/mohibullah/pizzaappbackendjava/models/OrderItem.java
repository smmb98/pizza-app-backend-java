package dev.mohibullah.pizzaappbackendjava.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int itemQty;

    @Column(nullable = false)
    private double productPrice;

    @Column(nullable = false)
    private double subTotalPrice;

    @Column(nullable = false)
    private String sizeMeasurement;

    @Column(nullable = false)
    private String sizeDescription;

    @Column(nullable = false)
    private String productName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order-item_topping")
    private List<Topping> topping;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private Size size;
}
