package com.example.SB_SmartNetStorage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.stereotype.Component;


import java.util.Date;

@Entity
@Component
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue
    private Long productId;
    //delete
    private String productName;
    //category
    private String productType;
    private String vendorName;

    //vendor
    private String serialNumber;
    private String partNumber;
    private String description;
    //storage
    private String storageName;
    private Date incomeDate;
    private String status;
    private Integer quantity;
    private boolean checkSerial;




    public Product() {

    }
}

