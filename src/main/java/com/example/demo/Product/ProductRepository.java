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

    //Custom Query
    @Query("SELECT p FROM Product p WHERE p.price < :maxPrice ")
    List<Product> findProductsWithPriceLessThan(@Param("maxPrice") double maxPrice);

    @Query("SELECT new com.example.demo.Product.Model.ProductDTO(p.name, p.description, p.price) FROM Product p")
    List<ProductDTO> getAllProductDTO();

    //Spring Data JPA
    List<Product> findByName(String name);
    List<Product> findByDescriptionContaining(String name, String description);
    List<Product> findByNameOrDescription(String name, String description);
    List<Product> findPriceLessThanOrderPriceAsc(Double maxPrice);

}
