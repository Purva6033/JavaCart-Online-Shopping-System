package com.javacart.service;

import java.util.List;

import com.javacart.dao.ProductDAO;
import com.javacart.model.Product;

public class ProductService {

    private ProductDAO dao = new ProductDAO();

    public boolean addProduct(Product product){

        return dao.addProduct(product);

    }

    public List<Product> getAllProducts(){

        return dao.getAllProducts();

    }
    public Product getProductById(int productId) {

        return dao.getProductById(productId);

    }
    public boolean updateProduct(Product product) {

        return dao.updateProduct(product);

    }
    public boolean deleteProduct(int productId) {

        return dao.deleteProduct(productId);

    }
    public List<Product> getProductsByCategory(String category){

        return dao.getProductsByCategory(category);

    }
    public List<Product> searchProducts(String keyword){

        return dao.searchProducts(keyword);

    }
    public List<Product> getProductsByPage(int start, int limit){

        return dao.getProductsByPage(start, limit);

    }

    public int getProductCount(){

        return dao.getProductCount();

    }

}