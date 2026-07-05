package com.javacart.controller;

import java.io.IOException;
import java.util.List;

import com.javacart.model.Product;
import com.javacart.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewProducts")
public class ViewProductsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductService service = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        
        List<Product> productList = service.getAllProducts();

        
        request.setAttribute("productList", productList);

        
        request.getRequestDispatcher("/admin/viewProducts.jsp")
               .forward(request, response);
    }

}