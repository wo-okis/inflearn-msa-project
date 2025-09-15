package com.wookis.orderservice.service;

import com.wookis.orderservice.dto.OrderDto;
import com.wookis.orderservice.entity.OrderEntity;
import com.wookis.orderservice.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
        orderDetails.setOrderId(UUID.randomUUID().toString());
        orderDetails.setTotalPrice(orderDetails.getUnitPrice() * orderDetails.getQty());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderEntity orderEntity = mapper.map(orderDetails, OrderEntity.class);
        ordersRepository.save(orderEntity);

        return mapper.map(orderEntity, OrderDto.class);
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity orderEntity = ordersRepository.findByOrderId(orderId);
        return new ModelMapper().map(orderEntity, OrderDto.class);
    }

    @Override
    public Iterable<OrderEntity> getOrdersByUserId(String userId) {
        return ordersRepository.findByUserId(userId);
    }
}
