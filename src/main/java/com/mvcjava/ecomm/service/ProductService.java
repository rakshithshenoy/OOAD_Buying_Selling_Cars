package com.mvcjava.ecomm.service;

import com.mvcjava.ecomm.product.Product;
import com.mvcjava.ecomm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

//     public Product storeproduct(String name, String category, String description, Integer price)
//    {
//        System.out.println("name "+ name+" cat "+category+" desc "+description+"  price "+price);
//        if (name == null || category == null || description == null || price == null)
//        {
//            System.out.println("something is null!!!!");
//            return null;
//        }
//        else
//        {
//            Product product = new Product();

//                   String filename = StringUtils.cleanPath(file.getOriginalFilename());
//         if (filename.contains("..")) {
//             System.out.println("not valid file");
//         }
//         try {
//             product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
//             // System.out.println("IMMMAAAGGGEEEEEE "+product.getImage());
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//            product.setName(name);
//            product.setCategory(category);
//            product.setDescription(description);
//            product.setPrice(price);
//            return productRepository.save(product);
//        }
//    }

    public List<Product> getAllproducts() {
        return productRepository.findAll();
    }

    public void storeproduct(MultipartFile file, String name, String category, String description, Integer price, Integer retailId) {
        Product product = new Product();
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (filename.contains("..")) {
            System.out.println("not valid file");
        }
        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            // System.out.println("IMMMAAAGGGEEEEEE "+product.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setRetailId(retailId);

        productRepository.save(product);

    }

    @Transactional
    public List<Product> getstationary() {
        return productRepository.findByCategory("suv");
    }

    //
    @Transactional
    public List<Product> getsports() {
        return productRepository.findByCategory("sports");
    }

    @Transactional
    public List<Product> getapplicance() {
        return productRepository.findByCategory("sedan");
    }

    @Transactional
    public List<Product> getgadgets() {
        return productRepository.findByCategory("hatchback");
    }

    @Transactional
    public List<Product> getProducts(String name) {
        List<Product> products = this.productRepository.getProductByName(name).orElseThrow(() -> new IllegalStateException("Product doesn't exist"));
        return products;
    }

    @Transactional
    public List<Product> getProdbyretId(Integer retID)
    {
        return productRepository.getProductByRetailId(retID);
    }
}
