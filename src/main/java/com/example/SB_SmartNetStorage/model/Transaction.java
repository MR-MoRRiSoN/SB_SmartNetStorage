package com.example.SB_SmartNetStorage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;
@Entity
@Component
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Transaction {
    @Id
    @GeneratedValue
    private Long historyId;
    private String vendorName;
    private String productType;
    private String serialNUmber;
    private String partNumber;
    private String storageName;
    private Date incomeDate;
    private Date operationDate;
    private String operatorName;
    private String operationName;

    public Transaction() {
    }

}
