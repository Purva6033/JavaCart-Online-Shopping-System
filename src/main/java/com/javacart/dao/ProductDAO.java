package com.javacart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.javacart.model.Product;
import com.javacart.utility.DBConnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public boolean addProduct(Product product) {

        boolean status = false;

        String query = "INSERT INTO products(product_name, description, category, price, stock_quantity, image_url) VALUES(?,?,?,?,?,?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
        ) {

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getCategory());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getStockQuantity());
            ps.setString(6, product.getImageUrl());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }
    
    public List<Product> getAllProducts() {

        List<Product> productList = new ArrayList<>();

        String query = "SELECT * FROM products";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ) {

            while (rs.next()) {

                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setImageUrl(rs.getString("image_url"));
                product.setCreatedAt(rs.getString("created_at"));

                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public Product getProductById(int productId) {

        Product product = null;

        String sql = "SELECT * FROM products WHERE product_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setImageUrl(rs.getString("image_url"));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return product;

    }
    
    public boolean updateProduct(Product product) {

        boolean status = false;

        String sql = "UPDATE products SET product_name=?, description=?, category=?, price=?, stock_quantity=?, image_url=? WHERE product_id=?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getCategory());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getStockQuantity());
            ps.setString(6, product.getImageUrl());
            ps.setInt(7, product.getProductId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    public boolean deleteProduct(int productId) {

        boolean status = false;

        String sql = "DELETE FROM products WHERE product_id=?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ps.setInt(1, productId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    public List<Product> getProductsByCategory(String category){

        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE category=?";

        try(
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ){

            ps.setString(1, category);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setImageUrl(rs.getString("image_url"));

                productList.add(product);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return productList;

    }
    
    public List<Product> searchProducts(String keyword) {

        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE product_name LIKE ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setImageUrl(rs.getString("image_url"));

                productList.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }
    public List<Product> getProductsByPage(int start, int limit) {

        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM products LIMIT ?, ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ps.setInt(1, start);
            ps.setInt(2, limit);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setImageUrl(rs.getString("image_url"));

                productList.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }
    public int getProductCount() {

        int count = 0;

        String sql = "SELECT COUNT(*) FROM products";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                count = rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return count;
    }
    
}