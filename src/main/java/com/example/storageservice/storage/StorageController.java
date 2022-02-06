package com.example.storageservice.storage;

import com.example.storageservice.allProducts.AllProductsService;
import com.example.storageservice.allProducts.ProductAllInfo;
import com.example.storageservice.model.DeliveryInfoList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/storage")
public class StorageController {
    Logger logger = LoggerFactory.getLogger(StorageController.class);

    private final StorageService storageService;
    private final AllProductsService allProductsService;

    @Autowired
    public StorageController(StorageService storageService, AllProductsService allProductsService) {
        this.storageService = storageService;
        this.allProductsService = allProductsService;
    }

    @GetMapping("/all")
    public DeliveryInfoList getAll() {
        logger.info("Getting Info about delivery");
        return storageService.getAll();
    }

    @GetMapping("/{id}")
    public Storage getById(@PathVariable(required = true) Long id) {
        logger.info("Getting delivery info about product with id " + id);
        return storageService.getDeliveryInfoByID(id);
    }

    @GetMapping("/download")
    public String downloadFile() {
        logger.info("Downloading csv file");
        List<ProductAllInfo> products = storageService.downloadCsv();
        allProductsService.addProducts(products);
        return "Success";
    }
}



