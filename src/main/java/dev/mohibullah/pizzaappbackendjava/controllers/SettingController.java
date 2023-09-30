package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.SettingRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SettingResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidSettingParamException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/setting")
public class SettingController {
    @PutMapping("updateSettings/{id}")
    public ResponseEntity<SettingResponseDTO> updateSettings(@PathVariable("id") int settingId, @RequestParam(value = "setting", defaultValue = "restaurant_info") String setting, @Valid @ModelAttribute SettingRequestDTO settingRequestDTO) throws IOException {

        if (!"restaurant_info".equals(setting) && !"app_setting".equals(setting)) {
            throw new InvalidSettingParamException();
        }

        SettingResponseDTO settingResponseDTO = new SettingResponseDTO();

        MultipartFile LOGO = settingRequestDTO.getRestaurant_LOGO();
        MultipartFile splashScreen = settingRequestDTO.getRestaurant_SplashImage();

        settingResponseDTO.setId(settingId);
        settingResponseDTO.setRestaurant_name(settingRequestDTO.getRestaurant_name());
        settingResponseDTO.setReviewSetting(Boolean.parseBoolean(settingRequestDTO.getReviewSetting()));
        settingResponseDTO.setAppleIdSignIn(Boolean.parseBoolean(settingRequestDTO.getAppleIdSignIn()));
        settingResponseDTO.setGoogleIdSignIn(Boolean.parseBoolean(settingRequestDTO.getGoogleIdSignIn()));
        settingResponseDTO.setPaymentMethod(Boolean.parseBoolean(settingRequestDTO.getAppleIdSignIn()));
        settingResponseDTO.setCurrency(settingRequestDTO.getCurrency());
        settingResponseDTO.setRestaurant_cuisines(settingRequestDTO.getRestaurant_cuisines());
        settingResponseDTO.setRestaurant_location(settingRequestDTO.getRestaurant_location());
        settingResponseDTO.setRestaurant_contactNos(List.of(settingRequestDTO.getRestaurant_contactNos().split(";")));
        settingResponseDTO.setRestaurant_socialLinks(List.of(settingRequestDTO.getRestaurant_socialLinks().split(";")));

        String[] timings = settingRequestDTO.getRestaurant_timings().split(";");
        String startTiming = timings[0];
        String endTiming = timings[1];

        String startMin = startTiming.substring(3, 5);
        String endMin = endTiming.substring(3, 5);

        int startHour = Integer.parseInt(startTiming.substring(0, 2));
        int endHour = Integer.parseInt(endTiming.substring(0, 2));

        startHour = (startHour % 12 == 0) ? 12 : startHour % 12;
        endHour = (endHour % 12 == 0) ? 12 : endHour % 12;

        String startPeriod = (Integer.parseInt(startTiming.substring(0, 2)) >= 12) ? "PM" : "AM";
        String endPeriod = (Integer.parseInt(endTiming.substring(0, 2)) >= 12) ? "PM" : "AM";

        String formattedStartTiming = String.format("%02d:%s%s", startHour, startMin, startPeriod);
        String formattedEndTiming = String.format("%02d:%s%s", endHour, endMin, endPeriod);

        settingResponseDTO.setRestaurant_timing_12HourFormat(new SettingResponseDTO.RestaurantTiming12HourFormat(formattedStartTiming, formattedEndTiming));
        settingResponseDTO.setRestaurant_timing_24HourFormat(new SettingResponseDTO.RestaurantTiming24HourFormat(startTiming, endTiming));

        File LOGOFile = new File("src/main/resources/static/LOGOs/", "LOGO.webp");
        try (OutputStream os = new FileOutputStream(LOGOFile)) {
            os.write(LOGO.getBytes());
        }
        File SplashScreenFile = new File("src/main/resources/static/SplashScreens/", "SplashScreen.webp");
        try (OutputStream os = new FileOutputStream(SplashScreenFile)) {
            os.write(splashScreen.getBytes());
        }

        return new ResponseEntity<>(settingResponseDTO, HttpStatus.CREATED);

    }

    @GetMapping("showSettings")
    public ResponseEntity<SettingResponseDTO> showSettings() {

        SettingResponseDTO settingResponseDTO = new SettingResponseDTO();


        settingResponseDTO.setId(1);
        settingResponseDTO.setRestaurant_name("Restaurant's Name here*");
        settingResponseDTO.setReviewSetting(false);
        settingResponseDTO.setAppleIdSignIn(true);
        settingResponseDTO.setGoogleIdSignIn(true);
        settingResponseDTO.setPaymentMethod(true);
        settingResponseDTO.setCurrency("$");
        settingResponseDTO.setRestaurant_cuisines("Restaurant's Cuisines here*");
        settingResponseDTO.setRestaurant_location("Restaurant's Location here*");
        settingResponseDTO.setRestaurant_contactNos(List.of("0000000;0000000".split(";")));
        settingResponseDTO.setRestaurant_socialLinks(List.of("https://www.facebook.com/pizza-app;https://www.youtube.com/@pizza-app;https://twitter.com/pizza-app;https://www.instagram.com/pizza-app;https://www.pizza-app.com".split(";")));

        String[] timings = "01:00;23:00".split(";");
        String startTiming = timings[0];
        String endTiming = timings[1];

        String startMin = startTiming.substring(3, 5);
        String endMin = endTiming.substring(3, 5);

        int startHour = Integer.parseInt(startTiming.substring(0, 2));
        int endHour = Integer.parseInt(endTiming.substring(0, 2));

        startHour = (startHour % 12 == 0) ? 12 : startHour % 12;
        endHour = (endHour % 12 == 0) ? 12 : endHour % 12;

        String startPeriod = (Integer.parseInt(startTiming.substring(0, 2)) >= 12) ? "PM" : "AM";
        String endPeriod = (Integer.parseInt(endTiming.substring(0, 2)) >= 12) ? "PM" : "AM";

        String formattedStartTiming = String.format("%02d:%s%s", startHour, startMin, startPeriod);
        String formattedEndTiming = String.format("%02d:%s%s", endHour, endMin, endPeriod);

        settingResponseDTO.setRestaurant_timing_12HourFormat(new SettingResponseDTO.RestaurantTiming12HourFormat(formattedStartTiming, formattedEndTiming));
        settingResponseDTO.setRestaurant_timing_24HourFormat(new SettingResponseDTO.RestaurantTiming24HourFormat(startTiming, endTiming));

        return new ResponseEntity<>(settingResponseDTO, HttpStatus.OK);

    }
}
