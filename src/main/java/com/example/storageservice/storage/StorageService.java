package com.example.storageservice.storage;

import com.example.storageservice.models.DeliveryInfoList;
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
        Optional<Storage> optionalStorage = storageRepository.findById(itemID);
        //TODO create custom exception e.g. StorageNotFoundException
        return optionalStorage.orElseThrow(() -> new NullPointerException("No storage found"));
    }

    public DeliveryInfoList getAll() {
        return new DeliveryInfoList(storageRepository.findAll());
    }

}
