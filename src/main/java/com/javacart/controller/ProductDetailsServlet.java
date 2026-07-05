package com.javacart.controller;

import java.io.IOException;

import com.javacart.model.Product;
import com.javacart.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/productDetails")
public class ProductDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductService service = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));

        Product product = service.getProductById(productId);

        if(product == null){

            response.sendRedirect(request.getContextPath() + "/customerHome");
            return;

        }

        request.setAttribute("product", product);

        request.getRequestDispatcher("/customer/productDetails.jsp")
               .forward(request, response);

    }
}