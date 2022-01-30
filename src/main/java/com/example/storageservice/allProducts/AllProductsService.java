package com.example.storageservice.allProducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllProductsService {
    private final AllProductsRepository allProductsRepository;

    @Autowired
    public AllProductsService(AllProductsRepository allProductsRepository) {
        this.allProductsRepository = allProductsRepository;
    }

    public String addProducts(List<ProductAllInfo> list) {
        for (ProductAllInfo product : list) {
            System.out.println(list);
            allProductsRepository.save(product);
        }
        return "Saved in DB";
    }
}
