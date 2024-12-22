package com.example.demo.Product;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import com.example.demo.Product.Model.UpdateProductCommand;
import com.example.demo.Product.commandHandlers.CreateProductCommandHandler;
import com.example.demo.Product.commandHandlers.DeleteProductCommandHandler;
import com.example.demo.Product.commandHandlers.UpdateProductCommandHandler;
import com.example.demo.Product.queryhandlers.GetAllProductsQueryHandler;
import com.example.demo.Product.queryhandlers.GetProductQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
