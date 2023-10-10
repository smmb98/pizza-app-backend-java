package dev.mohibullah.pizzaappbackendjava.services.Interfaces;

import dev.mohibullah.pizzaappbackendjava.dtos.request.SettingRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SettingResponseDTO;

import java.io.IOException;

public interface SettingServiceInterface {
    SettingResponseDTO updateSettings(SettingRequestDTO settingRequestDTO, int settingId, String settingType) throws IOException;

    SettingResponseDTO showSettings();
}
