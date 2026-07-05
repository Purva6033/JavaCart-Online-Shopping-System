package com.javacart.controller;

import java.io.IOException;
import java.util.List;

import com.javacart.model.CartItem;
import com.javacart.model.User;
import com.javacart.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("user") == null){

            response.sendRedirect(request.getContextPath()+"/customer/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        List<CartItem> cartList =
                cartService.getCartItems(user.getUserId());

        double grandTotal = 0;

        for(CartItem item : cartList){

            grandTotal += item.getPrice() * item.getQuantity();

        }

        request.setAttribute("cartList", cartList);
        request.setAttribute("grandTotal", grandTotal);
        session.setAttribute("totalAmount", grandTotal);

        request.getRequestDispatcher("/customer/checkout.jsp")
               .forward(request,response);

    }

}
