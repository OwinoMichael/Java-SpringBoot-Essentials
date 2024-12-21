package com.example.demo.products.queryhandlers;

import com.example.demo.Query;
import com.example.demo.products.Model.Product;
import com.example.demo.products.Model.ProductDTO;
import com.example.demo.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductQueryHandler implements Query <Integer, ProductDTO>{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ProductDTO> execute(Integer id) {
        Optional<Product> product = productRepository
                .findById(id);


        if(product.isEmpty()){
            throw new RuntimeException("Product not found");
        }


        return ResponseEntity.ok(new ProductDTO(product.get()));

    }
}
