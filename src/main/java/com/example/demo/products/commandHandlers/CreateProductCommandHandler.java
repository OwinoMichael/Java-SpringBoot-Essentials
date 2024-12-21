package com.example.demo.products.commandHandlers;

import com.example.demo.Command;
import com.example.demo.products.Model.Product;
import com.example.demo.products.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

@Service
public class CreateProductCommandHandler implements Command <Product, ResponseEntity>{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity execute(Product product) {

        validateProduct(product);

        productRepository.save(product);
        return ResponseEntity.ok().build();
    }


    private void validateProduct(Product product){
        if(StringUtils.isBlank(product.getName())){
            throw new RuntimeException("Product Name cannot be empty");
        }

        if(StringUtils.isBlank(product.getDescription())){
            throw new RuntimeException("Description cannot be empty");
        }


        if(product.getPrice() <= 0.0){
            throw new RuntimeException("Price cannot be negative");
        }


        if(product.getQuantity() <= 0.0){
            throw new RuntimeException("Quantity cannot be negative");
        }
    }
}