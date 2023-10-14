package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.response.DashboardResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidDateFormatException;
import dev.mohibullah.pizzaappbackendjava.services.implementations.DashboardServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardServiceImplementation dashboardServiceImplementation;

    @GetMapping("analytics")
    public ResponseEntity<DashboardResponseDTO> orderStats(
            @RequestParam(value = "startDate", defaultValue = "2000-01-01T00:00:00.000Z")
            String startDate,
            @RequestParam(value = "endDate", defaultValue = "3000-01-01T00:00:00.000Z")
            String endDate) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            LocalDateTime parsedStartDate = LocalDateTime.parse(startDate, formatter);
            LocalDateTime parsedEndDate = LocalDateTime.parse(endDate, formatter);

            return new ResponseEntity<>(dashboardServiceImplementation.orderStats(parsedStartDate, parsedEndDate), HttpStatus.OK);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }
}
