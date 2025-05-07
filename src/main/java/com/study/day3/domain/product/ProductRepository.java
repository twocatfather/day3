package com.study.day3.domain.product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(ProductId id);
}
