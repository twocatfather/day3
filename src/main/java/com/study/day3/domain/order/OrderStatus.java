package com.study.day3.domain.order;

public enum OrderStatus {
    CREATED,    // 주문 생성됨
    PENDING,    // 결제 대기 중
    PAID,       // 결제 완료
    PREPARING,  // 상품 준비 중
    SHIPPED,    // 배송 중
    DELIVERED,  // 배송 완료
    CANCELLED,  // 주문 취소 됨
    REFUNDED    // 환불 처리 됨
}
