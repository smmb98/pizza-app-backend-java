package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.SettingRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SettingResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidSettingParamException;
import dev.mohibullah.pizzaappbackendjava.services.implementations.SettingServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/setting")
public class SettingController {

    private final SettingServiceImplementation settingServiceImplementation;

    @PutMapping("updateSettings/{id}")
    public ResponseEntity<SettingResponseDTO> updateSettings(@PathVariable("id") int settingId, @RequestParam(value = "setting", defaultValue = "restaurant_info") String settingType, @Valid @ModelAttribute SettingRequestDTO settingRequestDTO) throws IOException {

        if (!"restaurant_info".equals(settingType) && !"app_setting".equals(settingType)) {
            throw new InvalidSettingParamException();
        }
        return new ResponseEntity<>(settingServiceImplementation.updateSettings(settingRequestDTO, settingId, settingType), HttpStatus.CREATED);
    }

    @GetMapping("showSettings")
    public ResponseEntity<SettingResponseDTO> showSettings() {

        return new ResponseEntity<>(settingServiceImplementation.showSettings(), HttpStatus.OK);
    }
}
