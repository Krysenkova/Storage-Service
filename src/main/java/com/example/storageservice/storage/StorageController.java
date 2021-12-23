package com.example.storageservice.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/storage")
public class StorageController {
    Logger logger = LoggerFactory.getLogger(StorageController.class);

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

//    @GetMapping("/all")
//    public List<Storage> getAll() {
//        return storageService.getAll();
//    }

    @GetMapping
    public Storage getDeliveryInfoByID(Long itemID) {
        return storageService.getDeliveryInfoByID(itemID);
    }
}



