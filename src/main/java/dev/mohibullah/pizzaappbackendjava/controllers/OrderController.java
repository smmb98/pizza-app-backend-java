package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.OrderItemRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.request.OrderRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.request.OrderStatusUpdateRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.OrderResponseDTO;
import dev.mohibullah.pizzaappbackendjava.enums.Stage;
import dev.mohibullah.pizzaappbackendjava.enums.Status;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidPaginationException;
import dev.mohibullah.pizzaappbackendjava.models.OrderItem;
import dev.mohibullah.pizzaappbackendjava.models.Product;
import dev.mohibullah.pizzaappbackendjava.models.Size;
import dev.mohibullah.pizzaappbackendjava.models.Topping;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @PostMapping("addOrder")
    public ResponseEntity<OrderResponseDTO> addOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setId(1);
        orderResponseDTO.setDeliveryCost(orderRequestDTO.getDeliveryCost());
        orderResponseDTO.setDeliveryAddress(orderRequestDTO.getDeliveryAddress());
        orderResponseDTO.setOrderPrice(orderRequestDTO.getOrderPrice());
        orderResponseDTO.setDiscount(orderRequestDTO.getDiscount());
        orderResponseDTO.setPaymentMethod(orderRequestDTO.getPaymentMethod());
        orderResponseDTO.setInstruction(orderRequestDTO.getInstruction());
        orderResponseDTO.setTotalPrice(orderRequestDTO.getTotalPrice());

        List<OrderItem> orderItemList = new ArrayList<>();
        List<OrderItemRequestDTO> orderItemRequestDTOs = orderRequestDTO.getOrderItems();

        for (int i = 0; i < orderItemRequestDTOs.size(); i++) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(i + 1);
            orderItem.setProductName(orderItemRequestDTOs.get(i).getProductName());
            orderItem.setItemQty(orderItemRequestDTOs.get(i).getItemQty());
            orderItem.setProductPrice(orderItemRequestDTOs.get(i).getProductPrice());
            orderItem.setSubTotalPrice(orderItemRequestDTOs.get(i).getSubTotalPrice());
            orderItem.setItemQty(orderItemRequestDTOs.get(i).getItemQty());
            orderItem.setSizeDescription(orderItemRequestDTOs.get(i).getSizeDescription());
            orderItem.setSizeMeasurement(orderItemRequestDTOs.get(i).getSizeMeasurement());

            Product product = new Product();
            product.setId(orderItemRequestDTOs.get(i).getProduct());
            product.setName("JAIN MASALA PIZZA");
            product.setImageName("JAIN_MASALA_PIZZA.webp");
            product.setDescription("Non-Garlic Onion Sauce, Cheese, Cilantro, Desi Achari Masala Sprinkled, Green Bell Pepper, Fresh Jalapeno");
            product.setStatus(Status.ACTIVE);
            orderItem.setProduct(product);


            List<Topping> toppingList = new ArrayList<>();
            for (int j = 0; j < orderItemRequestDTOs.get(i).getTopping().size(); j++) {
                Topping topping = new Topping();
                topping.setId(orderItemRequestDTOs.get(i).getTopping().get(j).get("id"));
                topping.setName("Fresh Jalapeno Marinara Sauce");
                topping.setCreatedAt(LocalDateTime.now());
                topping.setUpdatedAt(LocalDateTime.now());
                toppingList.add(topping);
            }
            orderItem.setTopping(toppingList);

            Size size = new Size();
            size.setId(orderItemRequestDTOs.get(i).getSize());
            size.setMeasurement("16");
            size.setDescription("X-Large");
            size.setCreatedAt(LocalDateTime.now());
            size.setUpdatedAt(LocalDateTime.now());
            orderItem.setSize(size);

            orderItemList.add(orderItem);
        }

        orderResponseDTO.setOrderItem(orderItemList);

        return new ResponseEntity<>(orderResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("showOrders")
    public ResponseEntity<BaseShowAllResponseDTO<OrderResponseDTO>> showOrders(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        if (page < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }

        List<OrderResponseDTO> orderList = new ArrayList<>();

        for (int k = 0; k < 5; k++) {
            OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
            orderResponseDTO.setId(k + 1);
            orderResponseDTO.setDeliveryCost(50.0);
            orderResponseDTO.setDeliveryAddress("R-43 Gulzar-e-Hijri scheme-30");
            orderResponseDTO.setOrderPrice(7.19);
            orderResponseDTO.setDiscount(0.0);
            orderResponseDTO.setPaymentMethod("VISA Card");
            orderResponseDTO.setInstruction("Adds extra cheese,toppings with extra");
            orderResponseDTO.setTotalPrice(57.19);
            orderResponseDTO.setOrderStage(Stage.ORDER_PLACED);

            List<OrderItem> orderItemList = new ArrayList<>();


            for (int i = 0; i < 2; i++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(i + 1);
                orderItem.setProductName("JAIN MASALA PIZZA");
                orderItem.setItemQty(1);
                orderItem.setProductPrice(12);
                orderItem.setSubTotalPrice(7.19);
                orderItem.setItemQty(1);
                orderItem.setSizeDescription("X-Large");
                orderItem.setSizeMeasurement("16");

                Product product = new Product();
                product.setId(i + 1);
                product.setName("JAIN MASALA PIZZA");
                product.setImageName("JAIN_MASALA_PIZZA.webp");
                product.setDescription("Non-Garlic Onion Sauce, Cheese, Cilantro, Desi Achari Masala Sprinkled, Green Bell Pepper, Fresh Jalapeno");
                product.setStatus(Status.ACTIVE);
                orderItem.setProduct(product);


                List<Topping> toppingList = new ArrayList<>();
                for (int j = 0; j < 2; j++) {
                    Topping topping = new Topping();
                    topping.setId(j + 1);
                    topping.setName("Fresh Jalapeno Marinara Sauce");
                    topping.setCreatedAt(LocalDateTime.now());
                    topping.setUpdatedAt(LocalDateTime.now());
                    toppingList.add(topping);
                }
                orderItem.setTopping(toppingList);

                Size size = new Size();
                size.setId(i + 1);
                size.setMeasurement("16");
                size.setDescription("X-Large");
                size.setCreatedAt(LocalDateTime.now());
                size.setUpdatedAt(LocalDateTime.now());
                orderItem.setSize(size);

                orderItemList.add(orderItem);
            }

            orderResponseDTO.setOrderItem(orderItemList);
            orderList.add(orderResponseDTO);
        }
        System.out.println(orderList);


        BaseShowAllResponseDTO<OrderResponseDTO> baseShowAllResponseDTO = new BaseShowAllResponseDTO<>();
        baseShowAllResponseDTO.setContent(orderList);
        baseShowAllResponseDTO.setPage(page);
        baseShowAllResponseDTO.setPageSize(pageSize);
        baseShowAllResponseDTO.setTotalPages(1);
        baseShowAllResponseDTO.setTotalElements(orderList.size());
        baseShowAllResponseDTO.setLast(true);
        return new ResponseEntity<>(baseShowAllResponseDTO, HttpStatus.OK);
    }

    @PutMapping("updateOrderStatus/{id}")
    public ResponseEntity<OrderResponseDTO> addOrder(@Valid @RequestBody OrderStatusUpdateRequestDTO orderStatusUpdateRequestDTO, @PathVariable("id") int orderId) {

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setId(orderId);
        orderResponseDTO.setDeliveryCost(50.0);
        orderResponseDTO.setDeliveryAddress("R-43 Gulzar-e-Hijri scheme-30");
        orderResponseDTO.setOrderPrice(7.19);
        orderResponseDTO.setDiscount(0.0);
        orderResponseDTO.setPaymentMethod("VISA Card");
        orderResponseDTO.setInstruction("Adds extra cheese,toppings with extra");
        orderResponseDTO.setTotalPrice(57.19);
        orderResponseDTO.setOrderStage(Objects.equals(orderStatusUpdateRequestDTO.getOrderStatus(), "Cancelled") ? Stage.CANCELLED : Stage.IN_PROGRESS);

        List<OrderItem> orderItemList = new ArrayList<>();


        for (int i = 0; i < 2; i++) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(i + 1);
            orderItem.setProductName("JAIN MASALA PIZZA");
            orderItem.setItemQty(1);
            orderItem.setProductPrice(12);
            orderItem.setSubTotalPrice(7.19);
            orderItem.setItemQty(1);
            orderItem.setSizeDescription("X-Large");
            orderItem.setSizeMeasurement("16");

            Product product = new Product();
            product.setId(i + 1);
            product.setName("JAIN MASALA PIZZA");
            product.setImageName("JAIN_MASALA_PIZZA.webp");
            product.setDescription("Non-Garlic Onion Sauce, Cheese, Cilantro, Desi Achari Masala Sprinkled, Green Bell Pepper, Fresh Jalapeno");
            product.setStatus(Status.ACTIVE);
            orderItem.setProduct(product);


            List<Topping> toppingList = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                Topping topping = new Topping();
                topping.setId(j + 1);
                topping.setName("Fresh Jalapeno Marinara Sauce");
                topping.setCreatedAt(LocalDateTime.now());
                topping.setUpdatedAt(LocalDateTime.now());
                toppingList.add(topping);
            }
            orderItem.setTopping(toppingList);

            Size size = new Size();
            size.setId(i + 1);
            size.setMeasurement("16");
            size.setDescription("X-Large");
            size.setCreatedAt(LocalDateTime.now());
            size.setUpdatedAt(LocalDateTime.now());
            orderItem.setSize(size);

            orderItemList.add(orderItem);
        }

        orderResponseDTO.setOrderItem(orderItemList);

        return new ResponseEntity<>(orderResponseDTO, HttpStatus.CREATED);
    }
}
