package dev.mohibullah.pizzaappbackendjava.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponseDTO {
    private OrderData order_data;
    private List<LineGraphData> line_graph_data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class OrderData {
        private int total_orders;
        private int inprogress_orders;
        private int completed_orders;
        private int cancelled_orders;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class LineGraphData {
        private String name;
        private List<List<Long>> data;
        private String color;
    }

}
