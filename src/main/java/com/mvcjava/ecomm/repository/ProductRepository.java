package com.mvcjava.ecomm.repository;

import com.mvcjava.ecomm.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer>
{
    List<Product> findByCategory(String category);
    Product findByName(String name);
    @Query("select p from Product p where p.name like concat('%',?1,'%')")
    public Optional<List<Product>> getProductByName(String name);

    List<Product> getProductByRetailId(Integer retID);
}
