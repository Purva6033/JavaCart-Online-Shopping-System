package com.javacart.controller;

import java.io.IOException;

import com.javacart.model.Product;
import com.javacart.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateProduct")
public class UpdateProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductService service = new ProductService();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Product product = new Product();

        product.setProductId(
                Integer.parseInt(request.getParameter("productId")));

        product.setProductName(
                request.getParameter("productName"));

        product.setDescription(
                request.getParameter("description"));

        product.setCategory(
                request.getParameter("category"));

        product.setPrice(
                Double.parseDouble(request.getParameter("price")));

        product.setStockQuantity(
                Integer.parseInt(request.getParameter("stockQuantity")));

        product.setImageUrl(
                request.getParameter("imageUrl"));

        boolean status = service.updateProduct(product);

        if (status) {

            response.sendRedirect(request.getContextPath() + "/viewProducts");

        } else {

            response.getWriter().println("<h2>Product Update Failed!</h2>");

        }

    }
}