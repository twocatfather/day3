package com.study.day3.domain.order;

import com.study.day3.domain.product.ProductId;
import com.study.day3.domain.shared.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    private Long id;
    private ProductId productId;
    private String productName;
    private Money price;
    private int quantity;
    private Money totalPrice;

    @Builder
    public OrderItem(ProductId productId,
                     String productName, Money price,
                     int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = price.multiply(quantity);

        validate();
    }

    private void validate() {

        // productid null check
        // productName
        // price null, price.isLessThanOrEqual(Money.ZERO)
        // quantity 0 과 비교
    }

    public static OrderItem create(ProductId productId, String productName,
                                   Money price, int quantity) {
        return OrderItem.builder()
                .productId(productId)
                .productName(productName)
                .price(price)
                .quantity(quantity)
                .build();
    }

    public static OrderItem reconstitute(Long id, ProductId productId, String productName,
                                         Money price, int quantity, Money totalPrice) {

        OrderItem orderItem = new OrderItem();
        orderItem.id = id;
        orderItem.productId = productId;
        orderItem.productName = productName;
        orderItem.price = price;
        orderItem.quantity = quantity;
        orderItem.totalPrice = totalPrice;

        return orderItem;
    }
}
