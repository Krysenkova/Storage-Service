package com.example.storageservice.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Component
public class StorageService {

    private final StorageRepository storageRepository;

    private RestTemplate restTemplate;

    @Autowired
    public StorageService(StorageRepository storageRepository, RestTemplate restTemplate) {
        this.storageRepository = storageRepository;
        this.restTemplate = restTemplate;
    }

    public Storage getDeliveryInfoByID(Long itemID) {
        return storageRepository.getById(itemID);
    }

    public List<Storage> getAll() {
        return storageRepository.findAll();
    }

}
