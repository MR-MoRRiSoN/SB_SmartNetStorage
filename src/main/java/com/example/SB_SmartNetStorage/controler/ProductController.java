package com.example.SB_SmartNetStorage.controler;

import com.example.SB_SmartNetStorage.exception.NotFoundException;
import com.example.SB_SmartNetStorage.model.Product;
import com.example.SB_SmartNetStorage.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct){return productRepository.save(newProduct);}

    @GetMapping("/getProduct")
    List<Product> getAllProduct(){return productRepository.findAll();}

    @GetMapping("/checkSerial/{serial}")
    List<Product> getAllProductSerial(@PathVariable Boolean serial){return productRepository.findByCheckSerial(serial);}


    @GetMapping("/getCollapse")
    List<String> getCollapse(){return productRepository.findDistinctProductTypes();}

    @GetMapping("/getProduct/{id}")
    Product getUserById(@PathVariable Long id){
        return productRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    @PutMapping("/setProduct/{id}")
    Product updateProduct(@RequestBody Product newProduct,@PathVariable Long id){
        return productRepository.findById(id).map(
                product -> {
                    product.setProductId(newProduct.getProductId());
                    product.setProductType(newProduct.getProductType());
                    product.setVendorName(newProduct.getVendorName());
                    product.setSerialNumber(newProduct.getSerialNumber());
                    product.setPartNumber(newProduct.getPartNumber());
                    product.setDescription(newProduct.getDescription());
                    product.setStorageName(newProduct.getStorageName());
                    product.setIncomeDate(newProduct.getIncomeDate());
                    product.setStatus(newProduct.getStatus());
                    return productRepository.save(product);
                }
        ).orElseThrow(()->new NotFoundException(id));


    }
    @DeleteMapping("/deleteUser/{id}")
    String deleteUser(@PathVariable Long id){
        if (!productRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        productRepository.deleteById(id);
        return "User With Id "+id+" Has Been Deleted Success.";
    }

}
