package com.javacart.controller;

import java.io.IOException;

import com.javacart.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/increaseQuantity")
public class IncreaseQuantityServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartService service = new CartService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int cartId =
                Integer.parseInt(request.getParameter("cartId"));

        service.increaseQuantity(cartId);

        response.sendRedirect(request.getContextPath()+"/myCart");
    }
}
