package com.javacart.controller;

import java.io.IOException;

import com.javacart.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductService service = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("id"));

        boolean status = service.deleteProduct(productId);

        if (status) {

            response.sendRedirect(request.getContextPath() + "/viewProducts");

        } else {

            response.getWriter().println("<h2>Product Delete Failed!</h2>");

        }
    }

}