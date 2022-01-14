package com.example.storageservice.allProducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/allproducts")
public class AllProductsController {

    private final AllProductsService allProductsService;

    @Autowired
    public AllProductsController(AllProductsService allProductsService) {
        this.allProductsService = allProductsService;
    }

    @PostMapping("/add")
    public String addAllProductsToDB(@RequestBody List<ProductAllInfo> products) {
        System.out.println(products);
        return allProductsService.addProducts(products);
    }
}
