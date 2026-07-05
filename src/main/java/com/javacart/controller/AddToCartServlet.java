package com.javacart.controller;

import java.io.IOException;

import com.javacart.model.Cart;
import com.javacart.model.User;
import com.javacart.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartService service = new CartService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

      
        HttpSession session = request.getSession(false);

        
        if(session == null || session.getAttribute("user") == null) {

            response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
            return;
        }

        
        User user = (User) session.getAttribute("user");

        
        int productId = Integer.parseInt(request.getParameter("productId"));

        
        Cart cart = new Cart();

        cart.setUserId(user.getUserId());
        cart.setProductId(productId);
        cart.setQuantity(1);

        
        boolean status = service.addToCart(cart);

        if(status){

            int cartCount = service.getCartCount(user.getUserId());

            session.setAttribute("cartCount", cartCount);

            response.sendRedirect(request.getContextPath() + "/customerHome");

        }
        else{

            response.getWriter().println("Failed to Add Product!");

        }

    }
}