package com.example.storageservice.allProducts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AllProductsRepository extends JpaRepository<ProductAllInfo, UUID> {
}
