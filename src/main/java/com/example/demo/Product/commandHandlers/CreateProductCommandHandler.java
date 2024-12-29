package com.example.demo.Product.commandHandlers;

import com.example.demo.Command;
import com.example.demo.Exceptions.ProductNotValidException;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class CreateProductCommandHandler implements Command <Product, ResponseEntity>{

    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(CreateProductCommandHandler.class);

    @Override
    public ResponseEntity execute(Product product) {
        logger.info("Executing " + getClass() + "with" + product.toString());
        validateProduct(product);

        productRepository.save(product);
        return ResponseEntity.ok().build();
    }


    private void validateProduct(Product product){
        if(StringUtils.isBlank(product.getName())){
            logger.error("Product Not Valid Exception was thrown (Invalid Name)" + product.toString());
            throw new ProductNotValidException("Product Name cannot be empty");
        }

        if(StringUtils.isBlank(product.getDescription())){
            logger.error("Product Not Valid Exception was thrown (Invalid Description)" + product.toString());
            throw new ProductNotValidException("Product description cannot be empty");
        }


        if(product.getPrice() <= 0.0){
            logger.error("Product Not Valid Exception was thrown (Invalid Price)" + product.toString());
            throw new ProductNotValidException("Product price cannot be negative");
        }


        if(product.getQuantity() <= 0.0){
            logger.error("Product Not Valid Exception was thrown (Invalid Quantity)" + product.toString());
            throw new ProductNotValidException("Product quantity cannot be negative");
        }
    }
}
