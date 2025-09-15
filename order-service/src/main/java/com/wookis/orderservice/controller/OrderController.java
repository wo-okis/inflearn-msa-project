package com.wookis.orderservice.controller;

import com.wookis.orderservice.dto.OrderDto;
import com.wookis.orderservice.dto.RequestOrder;
import com.wookis.orderservice.dto.ResponseOrder;
import com.wookis.orderservice.entity.OrderEntity;
import com.wookis.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service on LOCAL PORT %s (SERVER PORT %s)",
                env.getProperty("local.server.port"), env.getProperty("server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
                                                     @RequestBody RequestOrder requestOrder) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(requestOrder, OrderDto.class);
        orderDto.setUserId(userId);

        OrderDto createDto = orderService.createOrder(orderDto);
        ResponseOrder returnValue = mapper.map(createDto, ResponseOrder.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable("userId") String userId) {
        Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);

        ArrayList<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(v -> {
            result.add(new ResponseOrder(v));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
