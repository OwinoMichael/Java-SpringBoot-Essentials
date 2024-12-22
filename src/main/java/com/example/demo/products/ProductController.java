package com.example.demo.products;

import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.products.Model.Product;
import com.example.demo.products.Model.ProductDTO;
import com.example.demo.products.Model.UpdateProductCommand;
import com.example.demo.products.commandHandlers.CreateProductCommandHandler;
import com.example.demo.products.commandHandlers.DeleteProductCommandHandler;
import com.example.demo.products.commandHandlers.UpdateProductCommandHandler;
import com.example.demo.products.queryhandlers.GetAllProductsQueryHandler;
import com.example.demo.products.queryhandlers.GetProductQueryHandler;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")

public class ProductController {

    @Autowired private ProductRepository productRepository;

    @Autowired private GetAllProductsQueryHandler getAllProductsQueryHandler;

    @Autowired private GetProductQueryHandler getProductQueryHandler;

    @Autowired private CreateProductCommandHandler createProductCommandHandler;

    @Autowired UpdateProductCommandHandler updateProductCommandHandler;

    @Autowired DeleteProductCommandHandler deleteProductCommandHandler;

    //CRUD
    @GetMapping("/search/{maxPrice}")
    public ResponseEntity<List<Product>> findProductsByPrice(@PathVariable Double maxPrice){
       return ResponseEntity.ok(productRepository.findProductsWithPriceLessThan(maxPrice));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return getAllProductsQueryHandler.execute(null);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id){
        return getProductQueryHandler.execute(id);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product){
        return createProductCommandHandler.execute(product);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product){

        UpdateProductCommand command = new UpdateProductCommand(id, product);
        return updateProductCommandHandler.execute(command); //Execute method only takes one parameter so we abstract product and ID

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
       return deleteProductCommandHandler.execute(id);
    }


}
