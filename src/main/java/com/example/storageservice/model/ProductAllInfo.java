package com.example.storageservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "all_info")
@Getter @Setter
@ToString
public class ProductAllInfo {

    @Id
    @Column(name = "item_id")
    private Long itemId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "material")
    private String material;
    @Column(name = "colour")
    private String colour;
    @Column(name = "weight")
    private String weight;
    @Column(name = "price_without_vat")
    private Double priceWithoutVat;
    @Column(name = "price_with_vat")
    private Double priceWithVat;
    @Column(name = "delivery_time")
    private Long deliveryTime;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "product_location")
    private String location;

    //TODO: Info von External API

    public ProductAllInfo() {
    }

    public ProductAllInfo(Long itemId, String name, String description, String material, String colour, String weight,
                          Double priceWithoutVat, Double priceWithVat, Long deliveryTime, Integer amount, String location) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.material = material;
        this.colour = colour;
        this.weight = weight;
        this.priceWithoutVat = priceWithoutVat;
        this.priceWithVat = priceWithVat;
        this.deliveryTime = deliveryTime;
        this.amount = amount;
        this.location = location;
    }
}