package dev.mohibullah.pizzaappbackendjava.services.implementations;

import dev.mohibullah.pizzaappbackendjava.dtos.request.SettingRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SettingResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.ItemNotFoundException;
import dev.mohibullah.pizzaappbackendjava.models.Setting;
import dev.mohibullah.pizzaappbackendjava.repositories.SettingRepository;
import dev.mohibullah.pizzaappbackendjava.services.Interfaces.SettingServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SettingServiceImplementation implements SettingServiceInterface {

    private final SettingRepository settingRepository;

    @Override
    public SettingResponseDTO updateSettings(SettingRequestDTO settingRequestDTO, int settingId, String settingType) throws IOException {

        Setting setting = settingRepository.findById(settingId).orElseThrow(() -> new ItemNotFoundException("Setting could not be found"));

        settingRepository.save(mapToEntity(settingRequestDTO, setting, settingType));
        return mapToResponseDto(setting);
    }

    @Override
    public SettingResponseDTO showSettings() {
        Setting setting = settingRepository.findById(1).orElseThrow(() -> new ItemNotFoundException("Setting could not be found"));

        return mapToResponseDto(setting);
    }


    private SettingResponseDTO mapToResponseDto(Setting setting) {
        SettingResponseDTO settingResponseDTO = new SettingResponseDTO();

        settingResponseDTO.setId(setting.getId());
        settingResponseDTO.setRestaurant_name(setting.getRestaurant_name());
        settingResponseDTO.setReviewSetting(setting.isReviewSetting());
        settingResponseDTO.setAppleIdSignIn(setting.isAppleIdSignIn());
        settingResponseDTO.setGoogleIdSignIn(setting.isGoogleIdSignIn());
        settingResponseDTO.setPaymentMethod(setting.isPaymentMethod());
        settingResponseDTO.setCurrency(setting.getCurrency());
        settingResponseDTO.setRestaurant_cuisines(setting.getRestaurant_cuisines());
        settingResponseDTO.setRestaurant_location(setting.getRestaurant_location());
        settingResponseDTO.setRestaurant_contactNos(List.of(setting.getRestaurant_contactNos().split(";")));
        settingResponseDTO.setRestaurant_socialLinks(List.of(setting.getRestaurant_socialLinks().split(";")));

        String[] timings = setting.getRestaurant_timings().split(";");
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

        settingResponseDTO.setCreatedAt(setting.getCreatedAt());
        settingResponseDTO.setUpdatedAt(setting.getUpdatedAt());
        
        return settingResponseDTO;
    }


    private Setting mapToEntity(SettingRequestDTO settingRequestDTO, Setting setting, String settingType) throws IOException {


        if (Objects.equals(settingType, "app_setting")) {
            System.out.println(settingType);
            setting.setReviewSetting(Boolean.parseBoolean(settingRequestDTO.getReviewSetting()));
            setting.setAppleIdSignIn(Boolean.parseBoolean(settingRequestDTO.getAppleIdSignIn()));
            setting.setGoogleIdSignIn(Boolean.parseBoolean(settingRequestDTO.getGoogleIdSignIn()));
            setting.setPaymentMethod(Boolean.parseBoolean(settingRequestDTO.getPaymentMethod()));
            setting.setCurrency(settingRequestDTO.getCurrency());
        } else {
            System.out.println(settingType);
            MultipartFile LOGO = settingRequestDTO.getRestaurant_LOGO();
            MultipartFile splashScreen = settingRequestDTO.getRestaurant_SplashImage();

            setting.setRestaurant_cuisines(settingRequestDTO.getRestaurant_cuisines());
            setting.setRestaurant_location(settingRequestDTO.getRestaurant_location());
            setting.setRestaurant_contactNos(settingRequestDTO.getRestaurant_contactNos());
            setting.setRestaurant_socialLinks(settingRequestDTO.getRestaurant_socialLinks());
            setting.setRestaurant_name(settingRequestDTO.getRestaurant_name());
            setting.setRestaurant_timings(settingRequestDTO.getRestaurant_timings());
            setting.setRestaurant_description(settingRequestDTO.getRestaurant_description());


            File LOGOFile = new File("src/main/resources/static/LOGOs/", "LOGO.webp");
            try (OutputStream os = new FileOutputStream(LOGOFile)) {
                os.write(LOGO.getBytes());
            }
            File SplashScreenFile = new File("src/main/resources/static/SplashScreens/", "SplashScreen.webp");
            try (OutputStream os = new FileOutputStream(SplashScreenFile)) {
                os.write(splashScreen.getBytes());
            }

        }

        return setting;
    }
}
