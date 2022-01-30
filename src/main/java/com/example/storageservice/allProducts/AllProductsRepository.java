package com.example.storageservice.allProducts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AllProductsRepository extends JpaRepository<ProductAllInfo, Long> {
}
