package com.javacart.controller;

import java.io.IOException;

import com.javacart.model.Product;
import com.javacart.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductService service = new ProductService();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Read form data
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        String category = request.getParameter("category");

        double price = Double.parseDouble(request.getParameter("price"));

        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));

        String imageUrl = request.getParameter("imageUrl");

        // Create Product object
        Product product = new Product();

        product.setProductName(productName);
        product.setDescription(description);
        product.setCategory(category);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setImageUrl(imageUrl);

        // Call Service
        boolean status = service.addProduct(product);

        if (status) {

            response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");

        } else {

            response.getWriter().println("<h2>Product Not Added!</h2>");

        }

    }
}