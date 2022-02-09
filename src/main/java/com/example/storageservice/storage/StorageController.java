package com.example.storageservice.storage;

import com.example.storageservice.allProducts.AllProductsService;
import com.example.storageservice.allProducts.ProductAllInfo;
import com.example.storageservice.exceptions.NoStorageInfoFoundException;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get all storage info from DB")
    public StorageInfoList getAll() throws NoStorageInfoFoundException {
        StorageInfoList list = storageService.getAll();
        if (list != null) {
            return list;
        } else throw new NoStorageInfoFoundException("No storage info found");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get storage info for particular product from DB")
    public StorageInfo getById(@PathVariable(required = true) UUID id) throws NoStorageInfoFoundException {
        StorageInfo storageInfo = storageService.getStorageInfoByID(id);
        if (storageInfo != null) {
            return storageInfo;
        } else throw new NoStorageInfoFoundException("No item with id " + id + " was found");

    }

    @GetMapping("/download")
    @Operation(summary = "Download csv file from SFTP server")
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



