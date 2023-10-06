package dev.mohibullah.pizzaappbackendjava.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mohibullah.pizzaappbackendjava.enums.Stage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String deliveryAddress;

    @Column(nullable = false)
    private double orderPrice;

    @Column(nullable = false)
    private double deliveryCost;

    @Column(nullable = false)
    private double discount;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
//    @Column(columnDefinition = "ENUM('ORDER_PLACED', 'IN_PROGRESS', 'PREPARED', 'COMPLETED', 'CANCELLED')", nullable = false)
    @Column(nullable = false)
    private Stage orderStage;

    @Column
    private String instruction;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<OrderItem> orderItem = new ArrayList<OrderItem>();
}
