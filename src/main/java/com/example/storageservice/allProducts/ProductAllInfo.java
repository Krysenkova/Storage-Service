package com.example.storageservice.allProducts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "all_info")
@Getter
@Setter
@ToString
public class ProductAllInfo {
    @Id
    private UUID itemId;
    private String name;
    private String description;
    private String material;
    private String colour;
    private String weight;
    @Column(name = "price_without_vat")
    private Double priceWithoutMwSt;
    @Column(name = "price_with_vat")
    private Double priceWithMwSt;
    private Long deliveryTime;
    private Integer amount;
    @Column(name = "product_location")
    private String location;

    //TODO: Info von External API

    public ProductAllInfo() {
    }

    public ProductAllInfo(UUID itemId, String name, String description, String material, String colour, String weight,
                          Double priceWithoutMwSt, Double priceWithMwSt, Long deliveryTime, Integer amount, String location) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.material = material;
        this.colour = colour;
        this.weight = weight;
        this.priceWithoutMwSt = priceWithoutMwSt;
        this.priceWithMwSt = priceWithMwSt;
        this.deliveryTime = deliveryTime;
        this.amount = amount;
        this.location = location;
    }
}
