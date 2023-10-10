package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.OrderRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.request.OrderStatusUpdateRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.OrderResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidPaginationException;
import dev.mohibullah.pizzaappbackendjava.exceptions.OrderStageException;
import dev.mohibullah.pizzaappbackendjava.services.implementations.OrderServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderServiceImplementation orderServiceImplementation;

    @PostMapping("addOrder")
    public ResponseEntity<OrderResponseDTO> addOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {

        return new ResponseEntity<>(orderServiceImplementation.addOrder(orderRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("showOrders")
    public ResponseEntity<BaseShowAllResponseDTO<OrderResponseDTO>> showOrders(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                               @RequestParam(value = "stage", defaultValue = "current") String stage
    ) {
        if (page < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }

        if (!"current".equals(stage) && !"past".equals(stage)) {
            throw new OrderStageException("Invalid stage, must be either current or past");
        }

        return new ResponseEntity<>(orderServiceImplementation.showOrders(page, pageSize, stage), HttpStatus.OK);
    }

    @PutMapping("updateOrderStatus/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@Valid @RequestBody OrderStatusUpdateRequestDTO orderStatusUpdateRequestDTO, @PathVariable("id") int orderId) {

        return new ResponseEntity<>(orderServiceImplementation.updateOrderStatus(orderStatusUpdateRequestDTO, orderId), HttpStatus.CREATED);
    }
}
