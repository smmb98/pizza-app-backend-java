package dev.mohibullah.pizzaappbackendjava.services.Interfaces;

import dev.mohibullah.pizzaappbackendjava.dtos.request.OrderRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.request.OrderStatusUpdateRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.OrderResponseDTO;

public interface OrderServiceInterface {
    OrderResponseDTO addOrder(OrderRequestDTO orderRequestDTO);

    BaseShowAllResponseDTO<OrderResponseDTO> showOrders(int page, int pageSize, String stage);

    OrderResponseDTO updateOrderStatus(OrderStatusUpdateRequestDTO orderStatusUpdateRequestDTO, int orderId);
}
