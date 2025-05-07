package com.study.day3.infrastructure.persistence.product.repository;

import com.study.day3.infrastructure.persistence.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductRepository extends JpaRepository<ProductEntity, Long> {
}
