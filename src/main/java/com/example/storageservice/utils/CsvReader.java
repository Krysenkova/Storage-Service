package com.example.storageservice.utils;

import com.example.storageservice.allProducts.ProductAllInfo;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CsvReader {
    public CsvReader() {

    }

    public List<ProductAllInfo> readAllInfo(String filename) {
        List<ProductAllInfo> allInfo = new ArrayList<>();
        Path path = Paths.get(filename);

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split("; ");
                UUID itemId = UUID.fromString(attributes[0]);
                String name = attributes[1];
                String description = attributes[2];
                String material = attributes[3];
                String colour = attributes[4];
                String weight = attributes[5];
                Double priceWithoutMwSt = Double.parseDouble(attributes[6]);
                Double priceWithMwSt = Double.parseDouble(attributes[7]);
                Long deliveryTime = (long) Integer.parseInt(attributes[8]);
                Integer amount = Integer.parseInt(attributes[9]);
                String location = attributes[10];
                ProductAllInfo p = new ProductAllInfo(itemId, name, description, material, colour, weight, priceWithoutMwSt, priceWithMwSt, deliveryTime, amount, location);
                allInfo.add(p);
                line = br.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return allInfo;
    }
}
