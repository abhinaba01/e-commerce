package com.ecommerce.order_service.service;


import com.ecommerce.order_service.dto.OrderRequestDTO;
import com.ecommerce.order_service.dto.OrderResponseDTO;
import com.ecommerce.order_service.dto.ProductResponseDTO;
import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.entity.OrderItems;
import com.ecommerce.order_service.entity.OrderStatus;
import com.ecommerce.order_service.exception.*;
import com.ecommerce.order_service.mapper.OrderMapper;
import com.ecommerce.order_service.repository.OrderRepository;
import com.ecommerce.order_service.service.client.ProductClient;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final ProductClient productClient;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ProductClient productClient){

        this.orderRepository = orderRepository;

        this.orderMapper = orderMapper;
        this.productClient = productClient;
    }


    public void validateStocks(List<OrderItems> items,Map<String , ProductResponseDTO> productMap){

        for (OrderItems item : items) {

            ProductResponseDTO product = productMap.get(item.getProductId());
            Integer orderQuantity = item.getQuantity();

            if (product.getStockQuantity() < orderQuantity) {

               throw new InsufficientStocksException("Product " + product.getId() + "has insufficient Stocks");
            }
        }
    }


    public void reduceStocks(List<OrderItems> items,Map<String , ProductResponseDTO> productMap){

        for (OrderItems item : items) {
            ProductResponseDTO product = productMap.get(item.getProductId());
            Integer orderQuantity = item.getQuantity();

            productClient.reduceStock(product.getId(), orderQuantity);
            product.setStockQuantity(
                    product.getStockQuantity() - item.getQuantity()
            );

        }
    }

    public void enrichOrderItems(List<OrderItems> items,Map<String,ProductResponseDTO> productMap){

        for (OrderItems item : items) {
            ProductResponseDTO product = productMap.get(item.getProductId());
            BigDecimal itemPrice = product.getPrice();
            Integer orderQuantity = item.getQuantity();

            BigDecimal subTotal = itemPrice.multiply(
                    BigDecimal.valueOf(orderQuantity)
            );

            item.setProductName(product.getName());
            item.setProductPrice(itemPrice);
            item.setSellerId(product.getSellerId());

            item.setSubtotal(subTotal);

        }

    }

    public BigDecimal calculateTotal(List<OrderItems> items){

        BigDecimal total = BigDecimal.ZERO;

        for(OrderItems item:items){
            total = total.add(item.getSubtotal());

        }

        return total;
    }

    @Transactional
    public OrderResponseDTO createOrder(Long id , OrderRequestDTO request){


        Order newOrder = orderMapper.toEntity(request);

        newOrder.setCustomerId(id);

        if (newOrder.getItems() != null) {
            for (OrderItems item : newOrder.getItems()) {
                item.setOrder(newOrder);
            }
        }

        Map<String , ProductResponseDTO> productMap = new HashMap<>();

        for (OrderItems orderItems :newOrder.getItems()) {
            ProductResponseDTO product = productClient.getProductById(orderItems.getProductId());
            productMap.put(product.getId(), product);
        }

        validateStocks(newOrder.getItems(),productMap);
        reduceStocks(newOrder.getItems(),productMap);
        enrichOrderItems(newOrder.getItems(),productMap);
        BigDecimal total = calculateTotal(newOrder.getItems());


        newOrder.setTotalAmount(total);
        newOrder.setStatus(OrderStatus.CREATED);

        Order savedOrder = orderRepository.save(newOrder);

        return orderMapper.toDTO(savedOrder);

    }

    public OrderResponseDTO getOrderByID(UUID id){

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order Does Not Exist"));
        return orderMapper.toDTO(order);
    }

    public List<OrderResponseDTO> getOrderByCustomerID(Long id){

        List<Order> orderList = orderRepository.findByCustomerID(id);

        List<OrderResponseDTO> responseList = new ArrayList<>();

        for(Order order: orderList) {
            OrderResponseDTO response = orderMapper.toDTO(order);
            responseList.add(response);
        }

        return responseList;
    }


    @Transactional
    public OrderResponseDTO cancelOrderById(UUID id , Long customerId, String role){

        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order Does Not Exist"));



        if (!order.getCustomerId().equals(customerId) && (!role.equals("ADMIN"))) {
            throw new UnauthorizedOrderAccessException("User is not authorized to cancel order");
        }

        OrderStatus orderStatus = order.getStatus();

        if (orderStatus == OrderStatus.CANCELLED || orderStatus == OrderStatus.SHIPPED || orderStatus == OrderStatus.DELIVERED){
            throw new InvalidOrderStateException("Order cannot be cancelled");
        }

        for(OrderItems orderItems: order.getItems()){
            productClient.increaseStock(orderItems.getProductId(),orderItems.getQuantity());

        }

        order.setStatus(OrderStatus.CANCELLED);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);

    }

}
