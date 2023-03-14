package com.example.SB_SmartNetStorage.repository;
import com.example.SB_SmartNetStorage.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findBySerialNumber(String serialNumber);
    List<Product> findByPartNumber(String partNumber);

    @Query("select distinct  productType from  Product")
    ArrayList<String> findDistinctProductTypes();

    List<Product> findByProductType(String productType);
    List<Product> findByCheckSerial(Boolean checkSerial);

}
