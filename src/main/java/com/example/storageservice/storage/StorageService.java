package com.example.storageservice.storage;

import com.example.storageservice.allProducts.AllInfoList;
import com.example.storageservice.allProducts.ProductAllInfo;
import com.example.storageservice.models.DeliveryInfoList;
import com.example.storageservice.utils.CsvReader;
import com.example.storageservice.utils.FileTransferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class StorageService {

    private final StorageRepository storageRepository;
    private final FileTransferServiceImpl transfer;
    private RestTemplate restTemplate;

    @Autowired
    public StorageService(StorageRepository storageRepository, FileTransferServiceImpl transfer, RestTemplate restTemplate) {
        this.storageRepository = storageRepository;
        this.transfer = transfer;
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

    public List<ProductAllInfo> downloadCsv() {
        CsvReader reader = new CsvReader();
        transfer.downloadFile("all_info.csv", "all_info.csv");
        List<ProductAllInfo> list = reader.readAllInfo("all_info.csv");
        return list;
    }


}
