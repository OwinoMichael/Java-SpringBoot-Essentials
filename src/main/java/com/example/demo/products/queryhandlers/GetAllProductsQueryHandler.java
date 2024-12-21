package com.example.demo.products.queryhandlers;

import com.example.demo.Query;
import com.example.demo.products.Model.Product;
import com.example.demo.products.Model.ProductDTO;
import com.example.demo.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryHandler implements Query <Void, List<ProductDTO>>{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<ProductDTO> productDTOS = productRepository
                .findAll()
                .stream()
                .map(ProductDTO::new)
                .toList();

        return ResponseEntity.ok(productDTOS);
    }
}
