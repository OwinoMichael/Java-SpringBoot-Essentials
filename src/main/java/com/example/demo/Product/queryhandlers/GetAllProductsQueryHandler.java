package com.example.demo.Product.queryhandlers;

import com.example.demo.Query;
import com.example.demo.Product.Model.ProductDTO;
import com.example.demo.Product.ProductRepository;
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
//                .getAllProductDTO();
                .findAll()
                .stream()
                .map(ProductDTO::new)
                .toList();

        return ResponseEntity.ok(productDTOS);
    }
}
