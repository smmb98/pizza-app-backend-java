package dev.mohibullah.pizzaappbackendjava.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SettingResponseDTO {
    private int id;
    private boolean reviewSetting;
    private boolean paymentMethod;
    private boolean appleIdSignIn;
    private boolean googleIdSignIn;
    private String currency;
    private List<String> restaurant_contactNos;
    private String restaurant_description;
    private RestaurantTiming12HourFormat restaurant_timing_12HourFormat;
    private List<String> restaurant_socialLinks;
    private String restaurant_cuisines;
    private String restaurant_location;
    private String restaurant_name;
    private RestaurantTiming24HourFormat restaurant_timing_24HourFormat;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RestaurantTiming12HourFormat {
        private String start_timing;
        private String end_timing;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RestaurantTiming24HourFormat {
        private String start_timing;
        private String end_timing;

    }

}
