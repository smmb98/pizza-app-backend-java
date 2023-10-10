package dev.mohibullah.pizzaappbackendjava.services.implementations;

import dev.mohibullah.pizzaappbackendjava.dtos.request.OrderItemRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.request.OrderRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.request.OrderStatusUpdateRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.*;
import dev.mohibullah.pizzaappbackendjava.enums.Stage;
import dev.mohibullah.pizzaappbackendjava.exceptions.EmptyItemsListException;
import dev.mohibullah.pizzaappbackendjava.exceptions.ItemNotFoundException;
import dev.mohibullah.pizzaappbackendjava.exceptions.OrderStageException;
import dev.mohibullah.pizzaappbackendjava.models.*;
import dev.mohibullah.pizzaappbackendjava.repositories.*;
import dev.mohibullah.pizzaappbackendjava.services.Interfaces.OrderServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderServiceInterface {

    private final OrderRepository orderRepository;
    private final SizeRepository sizeRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ToppingRepository toppingRepository;

    @Override
    public OrderResponseDTO addOrder(OrderRequestDTO orderRequestDTO) {

        Order order = orderRepository.save(mapToEntity(orderRequestDTO));

        return mapToResponseDto(order);
    }

    @Override
    public BaseShowAllResponseDTO<OrderResponseDTO> showOrders(int page, int pageSize, String stage) {
        Stage[] currentStages = {Stage.ORDER_PLACED, Stage.IN_PROGRESS, Stage.PREPARED};
        Stage[] pastStages = {Stage.COMPLETED, Stage.CANCELLED};

        if (pageSize == 0) {

            Sort sort = Sort.by(Sort.Direction.ASC, "id");
            List<Order> allOrders;
            
            if ("current".equals(stage)) {
                allOrders = orderRepository.findByOrderStageIn(Arrays.asList(currentStages), sort);
            } else {
                allOrders = orderRepository.findByOrderStageIn(Arrays.asList(pastStages), sort);
            }


            if (allOrders.isEmpty()) {
                throw new EmptyItemsListException("No Orders in database");
            }

            List<OrderResponseDTO> content = allOrders.stream()
                    .map(order -> mapToResponseDto(order))
                    .toList();

            BaseShowAllResponseDTO<OrderResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(0);
            response.setPageSize(allOrders.size());
            response.setTotalElements(allOrders.size());
            response.setTotalPages(1);
            response.setLast(true);

            return response;
        } else {
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "id"));
            Page<Order> orders;

            if ("current".equals(stage)) {
                orders = orderRepository.findByOrderStageIn(Arrays.asList(currentStages), pageable);
            } else {
                orders = orderRepository.findByOrderStageIn(Arrays.asList(pastStages), pageable);
            }


            if (orders.getTotalElements() == 0) {
                throw new EmptyItemsListException("No Orders in database");
            }

            if (orders.isEmpty()) {
                throw new EmptyItemsListException("No Orders in page: " + orders.getNumber());
            }

            List<Order> listOfOrder = orders.getContent();
            List<OrderResponseDTO> content = listOfOrder.stream()
                    .map(order -> mapToResponseDto(order))
                    .toList();

            BaseShowAllResponseDTO<OrderResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(orders.getNumber());
            response.setPageSize(orders.getSize());
            response.setTotalElements(orders.getTotalElements());
            response.setTotalPages(orders.getTotalPages());
            response.setLast(orders.isLast());

            return response;
        }
    }


    @Override
    public OrderResponseDTO updateOrderStatus(OrderStatusUpdateRequestDTO orderStatusUpdateRequestDTO, int orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ItemNotFoundException("Order could not be found"));
        orderRepository.save(mapToEntity(orderStatusUpdateRequestDTO, order));
        return mapToResponseDto(order);
    }


    private OrderResponseDTO mapToResponseDto(Order order) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setId(order.getId());
        orderResponseDTO.setDeliveryCost(order.getDeliveryCost());
        orderResponseDTO.setDeliveryAddress(order.getDeliveryAddress());
        orderResponseDTO.setOrderPrice(order.getOrderPrice());
        orderResponseDTO.setDiscount(order.getDiscount());
        orderResponseDTO.setPaymentMethod(order.getPaymentMethod());
        orderResponseDTO.setInstruction(order.getInstruction());
        orderResponseDTO.setTotalPrice(order.getTotalPrice());
        orderResponseDTO.setOrderStage(order.getOrderStage());
        orderResponseDTO.setCreatedAt(order.getCreatedAt());
        orderResponseDTO.setUpdatedAt(order.getUpdatedAt());
        orderResponseDTO.setCreatedBy(order.getCreatedBy());
        orderResponseDTO.setUpdatedBy(order.getUpdatedBy());

        List<OrderItem> orderItemList = order.getOrderItem();
        List<OrderItemResponseDTO> orderItemResponseDTOList = new ArrayList<>();
        for (OrderItem item : orderItemList) {
            OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
            orderItemResponseDTO.setId(item.getId());
            orderItemResponseDTO.setItemQty(item.getItemQty());

            Product product = item.getProduct();
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setStatus(product.getStatus());
            productResponseDTO.setName(product.getName());
            productResponseDTO.setId(product.getId());
            productResponseDTO.setDescription(product.getDescription());
            productResponseDTO.setImageName(product.getImageName());
            productResponseDTO.setCreatedAt(product.getCreatedAt());
            productResponseDTO.setUpdatedAt(product.getUpdatedAt());
            orderItemResponseDTO.setProduct(productResponseDTO);

            Size size = item.getSize();
            SizeResponseDTO sizeResponseDTO = new SizeResponseDTO();
            sizeResponseDTO.setId(size.getId());
            sizeResponseDTO.setMeasurement(size.getMeasurement());
            sizeResponseDTO.setDescription(size.getDescription());
            sizeResponseDTO.setCreatedAt(size.getCreatedAt());
            sizeResponseDTO.setUpdatedAt(size.getUpdatedAt());
            orderItemResponseDTO.setSize(sizeResponseDTO);


            List<ToppingResponseDTO> toppingResponseDTOList = new ArrayList<>();
            for (Topping topping : item.getTopping()) {
                ToppingResponseDTO toppingResponseDTO = new ToppingResponseDTO();
                toppingResponseDTO.setId(topping.getId());
                toppingResponseDTO.setName(topping.getName());
                toppingResponseDTO.setPrice(topping.getPrice());
                toppingResponseDTO.setCreatedAt(topping.getCreatedAt());
                toppingResponseDTO.setUpdatedAt(topping.getUpdatedAt());
                toppingResponseDTOList.add(toppingResponseDTO);
            }
            orderItemResponseDTO.setTopping(toppingResponseDTOList);

            orderItemResponseDTO.setProductPrice(item.getProductPrice());
            orderItemResponseDTO.setSizeDescription(item.getSizeDescription());
            orderItemResponseDTO.setSizeMeasurement(item.getSizeMeasurement());
            orderItemResponseDTO.setSubTotalPrice(item.getSubTotalPrice());
            orderItemResponseDTO.setCreatedAt(item.getCreatedAt());
            orderItemResponseDTO.setUpdatedAt(item.getUpdatedAt());
            orderItemResponseDTO.setCreatedBy(item.getCreatedBy());
            orderItemResponseDTO.setUpdatedBy(item.getUpdatedBy());
            orderItemResponseDTOList.add(orderItemResponseDTO);
        }
        orderResponseDTO.setOrderItem(orderItemResponseDTOList);


        return orderResponseDTO;
    }

    private Order mapToEntity(OrderRequestDTO orderRequestDTO) {
        User user = userRepository.findById(1)
                .orElseThrow(() -> new ItemNotFoundException("User could not be found"));

        Order order = new Order();

        order.setDeliveryCost(orderRequestDTO.getDeliveryCost());
        order.setDeliveryAddress(orderRequestDTO.getDeliveryAddress());
        order.setOrderPrice(orderRequestDTO.getOrderPrice());
        order.setDiscount(orderRequestDTO.getDiscount());
        order.setPaymentMethod(orderRequestDTO.getPaymentMethod());
        order.setInstruction(orderRequestDTO.getInstruction());
        order.setTotalPrice(orderRequestDTO.getTotalPrice());
        order.setOrderStage(Stage.ORDER_PLACED);
        order.setCreatedBy(user);
        order.setUpdatedBy(user);

        List<OrderItem> orderItemsToSave = new ArrayList<>();
        List<OrderItemRequestDTO> orderItemRequestDTOs = orderRequestDTO.getOrderItems();

        for (OrderItemRequestDTO orderItemRequestDTO : orderItemRequestDTOs) {

            Product product = productRepository.findById(orderItemRequestDTO.getProduct())
                    .orElseThrow(() -> new ItemNotFoundException("Product could not be found"));

            Size size = sizeRepository.findById(orderItemRequestDTO.getSize())
                    .orElseThrow(() -> new ItemNotFoundException("Size could not be found"));

            List<Topping> toppingList = new ArrayList<>();
            for (int j = 0; j < orderItemRequestDTO.getTopping().size(); j++) {
                Topping topping = toppingRepository.findById(orderItemRequestDTO.getTopping().get(j).get("id"))
                        .orElseThrow(() -> new ItemNotFoundException("Topping could not be found"));
                toppingList.add(topping);
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setProductName(orderItemRequestDTO.getProductName());
            orderItem.setItemQty(orderItemRequestDTO.getItemQty());
            orderItem.setProductPrice(orderItemRequestDTO.getProductPrice());
            orderItem.setSubTotalPrice(orderItemRequestDTO.getSubTotalPrice());
            orderItem.setItemQty(orderItemRequestDTO.getItemQty());
            orderItem.setSizeDescription(orderItemRequestDTO.getSizeDescription());
            orderItem.setSizeMeasurement(orderItemRequestDTO.getSizeMeasurement());
            orderItem.setProduct(product);
            orderItem.setSize(size);
            orderItem.setTopping(toppingList);
            orderItem.setCreatedBy(user);
            orderItem.setUpdatedBy(user);

            orderItemsToSave.add(orderItem);
        }
        order.setOrderItem(orderItemsToSave);

        return order;
    }

    private Order mapToEntity(OrderStatusUpdateRequestDTO orderStatusUpdateRequestDTO, Order order) {
        User user = userRepository.findById(1)
                .orElseThrow(() -> new ItemNotFoundException("User could not be found"));

        if (order.getOrderStage() == Stage.COMPLETED || order.getOrderStage() == Stage.CANCELLED) {
            System.out.println("ashere");
            throw new OrderStageException("Cannot update " + order.getOrderStage() + " order");
        }

        String orderStatus = orderStatusUpdateRequestDTO.getOrderStatus();
        if (orderStatus == null || orderStatus.isEmpty()) {
            order.setOrderStage(order.getOrderStage().next());
        } else {
            order.setOrderStage(Stage.CANCELLED);
        }

        order.setUpdatedBy(user);

        return order;
    }
}
