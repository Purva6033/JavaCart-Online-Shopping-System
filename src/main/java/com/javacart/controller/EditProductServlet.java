package com.javacart.controller;

import java.io.IOException;

import com.javacart.model.Product;
import com.javacart.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editProduct")
public class EditProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductService service = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Read product id from URL
        int productId = Integer.parseInt(request.getParameter("id"));

        // Fetch product from database
        Product product = service.getProductById(productId);

        // Send product to JSP
        request.setAttribute("product", product);

        // Open edit page
        request.getRequestDispatcher("/admin/editProduct.jsp")
               .forward(request, response);
    }
}