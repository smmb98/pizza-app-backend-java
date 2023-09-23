package dev.mohibullah.pizzaappbackendjava.models;

import dev.mohibullah.pizzaappbackendjava.enums.Status;
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
public class Product extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000,nullable = false)
    private String description;

//    @Column()
//    private String imageName;
//
//    @Column()
//    private String imageUUID;

    @Enumerated(EnumType.STRING)
//    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE')", nullable = false)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Products_Sizes_Prices> productsSizesPrices = new ArrayList<Products_Sizes_Prices>();
}
