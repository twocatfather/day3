package com.study.day3.domain.order;

import com.study.day3.domain.shared.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    private OrderId id;
    private String customerName;
    private String customerEmail;
    private String shippingAddress;
    private OrderStatus status;
    private List<OrderItem> orderItems = new ArrayList<>();
    private Money totalAmount;
    private LocalDateTime orderedAt;
    private LocalDateTime updatedAt;

    @Builder
    public Order(String customerName, String customerEmail,
                 String shippingAddress, List<OrderItem> orderItems) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.shippingAddress = shippingAddress;
        this.status = OrderStatus.CREATED;
        this.orderItems = orderItems;
        this.totalAmount = calculateTotalAmount();
        this.orderedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        validate();
    }

    private void validate() {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("고객 이름은 필수입니다.");
        }

        // customer email
        // shipping address
        // orderItems
    }

    private Money calculateTotalAmount() {
        return orderItems.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(Money.ZERO, Money::add);
    }
}
