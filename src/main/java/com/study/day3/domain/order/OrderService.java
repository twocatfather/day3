package com.study.day3.domain.order;

import com.study.day3.domain.product.Product;
import com.study.day3.domain.product.ProductId;
import com.study.day3.domain.product.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Order createOrder(String customerName, String customerEmail
            , String shippingAddress, List<OrderItemRequest> orderItems) {

        // 1. 각 상품의 재고 확인 및 OrderItem 생성
        List<OrderItem> orderItemList = new ArrayList<>();

        for (OrderItemRequest itemRequest : orderItems) {
            ProductId productId = ProductId.of(itemRequest.getProductId());
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("물품이 없습니다." + itemRequest.getProductId()));

            if (product.getStockQuantity() < itemRequest.getQuantity()) {
                throw new IllegalStateException("재고가 부족합니다.");
            }

            OrderItem orderItem = OrderItem.create(
                    productId,
                    product.getName(),
                    product.getPrice(),
                    itemRequest.getQuantity()
            );

            orderItemList.add(orderItem);

            // 재고감소
            product.decreaseStock(itemRequest.getQuantity());
        }

        Order order = Order.create(customerName, customerEmail, shippingAddress, orderItemList);
        return orderRepository.save(order);
    }

    public Order cancelOrder(OrderId id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("없는 주문입니다"));

        order.cancel();

        for (OrderItem item : order.getOrderItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("물품이 없습니다."));

            product.increaseStock(item.getQuantity());
        }
        return order;
    }

    @Getter
    @AllArgsConstructor
    public static class OrderItemRequest {
        private Long productId;
        private int quantity;
    }
}
