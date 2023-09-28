package dev.mohibullah.pizzaappbackendjava.dtos.request;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations.CustomImage;
import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations.CustomPhoneNumbers;
import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations.CustomSocialLinks;
import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations.CustomTimings;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingRequestDTO {
    @NotBlank(message = "Review Setting Required")
    @Pattern(regexp = "^(true|false)$", message = "Invalid value. Must be 'true' or 'false'")
    private String reviewSetting;

    @NotBlank(message = "Payment Method Required")
    @Pattern(regexp = "^(true|false)$", message = "Invalid value. Must be 'true' or 'false'")
    private String paymentMethod;

    @NotBlank(message = "Apple-ID SignIn Required")
    @Pattern(regexp = "^(true|false)$", message = "Invalid value. Must be 'true' or 'false'")
    private String appleIdSignIn;

    @NotBlank(message = "Google-ID SignIn Required")
    @Pattern(regexp = "^(true|false)$", message = "Invalid value. Must be 'true' or 'false'")
    private String googleIdSignIn;

    @NotBlank(message = "Currency Required")
    private String currency;

    @NotBlank(message = "Restaurant description cannot be empty")
    private String restaurant_description;

    @NotBlank(message = "Restaurant contact numbers cannot be empty")
    @CustomPhoneNumbers(message = "Invalid format for restaurant contact numbers. Please enter in format: '0000000;0000000'")
    private String restaurant_contactNos;

    @NotBlank(message = "Restaurant timings cannot be empty")
    @CustomTimings(message = "Invalid format for restaurant timings. Please enter in format: '12:00;14:00'")
    private String restaurant_timings;

    @NotBlank(message = "Social links cannot be empty")
    @CustomSocialLinks(message = "Invalid social links format. Please enter 5 social links in the format: 'https://www.facebook.com/pizza-app;https://www.youtube.com/@pizza-app;https://twitter.com/pizza-app;https://www.instagram.com/pizza-app;https://www.pizza-app.com'")
    private String restaurant_socialLinks;

    @NotBlank(message = "Restaurant cuisines cannot be empty")
    private String restaurant_cuisines;

    @NotBlank(message = "Restaurant location cannot be empty")
    private String restaurant_location;

    @NotBlank(message = "Restaurant name cannot be empty")
    private String restaurant_name;

    @NotNull(message = "LOGO is required")
    @CustomImage(message = "LOGO must be of type webp")
    private MultipartFile restaurant_LOGO;

    @NotNull(message = "SplashImage is required")
    @CustomImage(message = "SplashImage must be of type webp")
    private MultipartFile restaurant_SplashImage;

}
