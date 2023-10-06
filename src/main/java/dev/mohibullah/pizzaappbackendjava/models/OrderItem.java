package dev.mohibullah.pizzaappbackendjava.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonManagedReference
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonManagedReference
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
    @JsonManagedReference
    private List<Topping> topping;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    @JsonManagedReference
    private Size size;
}
