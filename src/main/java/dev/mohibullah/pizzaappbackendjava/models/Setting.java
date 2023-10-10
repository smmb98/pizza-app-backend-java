package dev.mohibullah.pizzaappbackendjava.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Setting extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private boolean reviewSetting;

    @Column(nullable = false)
    private boolean paymentMethod;

    @Column(nullable = false)
    private boolean appleIdSignIn;

    @Column(nullable = false)
    private boolean googleIdSignIn;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String restaurant_contactNos;

    @Column(length = 2000, nullable = false)
    private String restaurant_description;

    @Column(nullable = false)
    private String restaurant_timings;

    @Column
    private String restaurant_socialLinks;

    @Column(nullable = false)
    private String restaurant_cuisines;

    @Column(nullable = false)
    private String restaurant_location;

    @Column(nullable = false)
    private String restaurant_name;

}
