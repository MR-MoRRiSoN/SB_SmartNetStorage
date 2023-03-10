package com.example.SB_SmartNetStorage.controler;

import com.example.SB_SmartNetStorage.model.Product;
import com.example.SB_SmartNetStorage.model.Transaction;
import com.example.SB_SmartNetStorage.repository.ProductRepository;
import com.example.SB_SmartNetStorage.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    public TransactionController(TransactionRepository transactionRepository, ProductRepository productRepository){this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/addTransaction/{uniCode}/{quantity}")
    Object newTransaction(@RequestBody Transaction newTransaction, @PathVariable String uniCode, @PathVariable Integer quantity){
        Calendar calendar = Calendar.getInstance();
        Date dataTimeNow = calendar.getTime();
        newTransaction.setOperationDate(dataTimeNow);


       List<Product> product = productRepository.findBySerialNumber(uniCode);
        if (product.get(0).getQuantity()<quantity){
            return "ERROR In Database";
        }

       productRepository.findById(product.get(0).getProductId()).map(
               edtProduct -> {
                   edtProduct.setQuantity(edtProduct.getQuantity()-quantity);
                   return productRepository.save(edtProduct);
               }
       );

        return transactionRepository.save(newTransaction);
    }
    @GetMapping("/getTransaction")
    List<Transaction> getAllTransaction(){return  transactionRepository.findAll();}


}
