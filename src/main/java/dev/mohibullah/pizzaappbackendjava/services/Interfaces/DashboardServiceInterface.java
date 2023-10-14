package dev.mohibullah.pizzaappbackendjava.services.Interfaces;

import dev.mohibullah.pizzaappbackendjava.dtos.response.DashboardResponseDTO;

import java.time.LocalDateTime;

public interface DashboardServiceInterface {
    DashboardResponseDTO orderStats(LocalDateTime startDate, LocalDateTime endDate);

}
