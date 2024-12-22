package com.example.demo.products;


import com.example.demo.products.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.price < :maxPrice ")
    List<Product> findProductsWithPriceLessThan(@Param("maxPrice") double maxPrice);

}
