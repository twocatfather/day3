package com.study.day3.infrastructure.persistence.order.repository;

import com.study.day3.domain.order.Order;
import com.study.day3.domain.order.OrderId;
import com.study.day3.domain.order.OrderRepository;
import com.study.day3.domain.order.OrderStatus;
import com.study.day3.infrastructure.persistence.order.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaOrderRepository implements OrderRepository {
    private final SpringDataOrderRepository repository;

    @Override
    public Order save(Order order) {
        OrderEntity entity = OrderEntity.from(order);
        OrderEntity savedEntity = repository.save(entity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return repository.findById(id.getValue())
                .map(OrderEntity::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll()
                .stream()
                .map(OrderEntity::toDomain)
                .toList();
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return repository.findByStatus(status)
                .stream().map(OrderEntity::toDomain)
                .toList();
    }

    @Override
    public List<Order> findByCustomerEmail(String email) {
        return repository.findByCustomerEmail(email)
                .stream().map(OrderEntity::toDomain)
                .toList();
    }

    @Override
    public List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByOrderDateBetween(startDate, endDate)
                .stream().map(OrderEntity::toDomain)
                .toList();
    }

    @Override
    public void delete(Order order) {
        repository.deleteById(order.getId().getValue());
    }
}
