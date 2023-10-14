package dev.mohibullah.pizzaappbackendjava.services.implementations;

import dev.mohibullah.pizzaappbackendjava.dtos.response.DashboardResponseDTO;
import dev.mohibullah.pizzaappbackendjava.enums.Stage;
import dev.mohibullah.pizzaappbackendjava.models.Order;
import dev.mohibullah.pizzaappbackendjava.repositories.OrderRepository;
import dev.mohibullah.pizzaappbackendjava.services.Interfaces.DashboardServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImplementation implements DashboardServiceInterface {

    private final OrderRepository orderRepository;

    @Override
    public DashboardResponseDTO orderStats(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderRepository.findByCreatedAtBetween(startDate, endDate, Sort.by(Sort.Direction.ASC, "createdAt"));
        return mapToResponseDto(orders);
    }

    private DashboardResponseDTO mapToResponseDto(List<Order> orders) {
        DashboardResponseDTO dashboardResponseDTO = new DashboardResponseDTO();

        int total_orders = 0;
        int inprogress_orders = 0;
        int completed_orders = 0;
        int cancelled_orders = 0;

        // Initialize line graph data for Completed and Cancelled orders
        DashboardResponseDTO.LineGraphData completedData = new DashboardResponseDTO.LineGraphData("Completed", new ArrayList<>(), "#02FF2C");
        DashboardResponseDTO.LineGraphData cancelledData = new DashboardResponseDTO.LineGraphData("Cancelled", new ArrayList<>(), "#FF00008B");


        if (!orders.isEmpty()) {
            LocalDateTime startDate = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
            LocalDateTime endDate = startDate;

            for (Order order : orders) {
                if ((order.getOrderStage() == Stage.COMPLETED || order.getOrderStage() == Stage.CANCELLED) && startDate.equals(LocalDateTime.of(2000, 1, 1, 0, 0, 0))) {
                    startDate = order.getCreatedAt();
                }
                if (order.getOrderStage() == Stage.COMPLETED || order.getOrderStage() == Stage.CANCELLED) {
                    endDate = order.getCreatedAt();
                }
            }

            // Create a map to store order counts by date
            Map<LocalDate, Map<Stage, Integer>> orderCountsByDate = new HashMap<>();

            // Populate orderCountsByDate with initial values
            for (LocalDate date = startDate.toLocalDate(); date.isBefore(endDate.toLocalDate().plusDays(1)); date = date.plusDays(1)) {
                orderCountsByDate.put(date, new HashMap<>());
                orderCountsByDate.get(date).put(Stage.COMPLETED, 0);
                orderCountsByDate.get(date).put(Stage.CANCELLED, 0);
            }

            // Populate orderCountsByDate based on the actual order stages
            for (Order order : orders) {
                LocalDate orderDate = order.getCreatedAt().toLocalDate();
                Stage orderStage = order.getOrderStage();

                if (orderStage == Stage.COMPLETED || orderStage == Stage.CANCELLED) {
                    orderCountsByDate.get(orderDate).put(orderStage, orderCountsByDate.get(orderDate).get(orderStage) + 1);
                }

                if (orderStage == Stage.COMPLETED) {
                    completed_orders++;
                } else if (orderStage == Stage.CANCELLED) {
                    cancelled_orders++;
                } else if (orderStage == Stage.IN_PROGRESS || orderStage == Stage.ORDER_PLACED || orderStage == Stage.PREPARED) {
                    inprogress_orders++;
                }
                total_orders++;
            }


            // Calculate the total orders, in-progress orders, completed orders, and cancelled orders
            for (LocalDate date = startDate.toLocalDate(); date.isBefore(endDate.toLocalDate().plusDays(1)); date = date.plusDays(1)) {

                // Add data to line graph for Completed and Cancelled orders
                completedData.getData().add(List.of(date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli(), (long) orderCountsByDate.get(date).get(Stage.COMPLETED)));
                cancelledData.getData().add(List.of(date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli(), (long) orderCountsByDate.get(date).get(Stage.CANCELLED)));
            }

            // Set the order data and line graph data in the response DTO
            dashboardResponseDTO.setOrder_data(new DashboardResponseDTO.OrderData(total_orders, inprogress_orders, completed_orders, cancelled_orders));
            dashboardResponseDTO.setLine_graph_data(List.of(completedData, cancelledData));
        } else {
            // Handle the case when there are no orders
            DashboardResponseDTO.OrderData orderData = new DashboardResponseDTO.OrderData(total_orders, inprogress_orders, completed_orders, cancelled_orders);
            List<DashboardResponseDTO.LineGraphData> lineGraphDataList = new ArrayList<>();
            lineGraphDataList.add(completedData);
            lineGraphDataList.add(cancelledData);

            dashboardResponseDTO = new DashboardResponseDTO(orderData, lineGraphDataList);
        }

        return dashboardResponseDTO;
    }
}