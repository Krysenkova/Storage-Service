package com.example.storageservice.storage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "delivery_info")
@Getter @Setter
@ToString
public class Storage {

    @Id
    //sequence generator? do we need it?
    //@GeneratedValue(generator = "uuid")
    @Column(name = "item_id")   //do not know if we really need column annotations
    private Long itemId;
    @Column(name = "deliverytime")
    private Long deliveryTime;
    @Column(name="amount")
    private Integer amount;
    @Column(name="product_location")
    private String location;

    public Storage(){

    }

    public Storage(Long itemId, Long deliveryTime, Integer amount, String location) {
        this.itemId = itemId;
        this.deliveryTime = deliveryTime;
        this.amount = amount;
        this.location = location;
    }
}
