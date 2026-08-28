//package com.ecommerce.order_service.service;
//
//import com.ecommerce.events.order.OrderCreatedEvent;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaEventPublisher {
//
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//
//    public KafkaEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void publishOrderCreated(OrderCreatedEvent event) {
//
//        kafkaTemplate.send("order-created", event);
//
//        System.out.println("Published event: " + event);
//    }
//}