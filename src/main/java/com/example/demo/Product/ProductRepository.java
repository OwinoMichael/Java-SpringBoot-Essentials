package com.example.demo.Product;


import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //Custom Query we write our own
    @Query("SELECT p FROM Product p WHERE p.description LIKE %:description%")
    List<Product> customQueryMethod(@Param(value="description") String description);

    //Spring Data JPA
    List<Product> findByDescriptionContainingIgnoreCase(String keyword);



}
