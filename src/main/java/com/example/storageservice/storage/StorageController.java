package com.example.storageservice.storage;

import com.example.storageservice.allProducts.AllProductsService;
import com.example.storageservice.allProducts.ProductAllInfo;

import com.example.storageservice.model.DeliveryInfoList;
import com.example.storageservice.exceptions.NoStorageFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public DeliveryInfoList getAll() throws NoStorageFoundException {
        DeliveryInfoList list = storageService.getAll();
        if (list != null) {
            return list;
        } else throw new NoStorageFoundException("No storage info found");
    }

    @GetMapping("/{id}")
    public Storage getById(@PathVariable(required = true) UUID id) throws NoStorageFoundException {
        Storage storage = storageService.getDeliveryInfoByID(id);
        if (storage != null) {
            return storage;
        } else throw new NoStorageFoundException("No item with id " + id + " was found");

    }

    @GetMapping("/download")
    public String downloadFile() {
        logger.info("Downloading csv file");
        List<ProductAllInfo> products = storageService.downloadCsv();
        if (products != null) {
            allProductsService.addProducts(products);
            return "Success";
        }
        return "File came empty";
    }
}



