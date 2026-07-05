package com.javacart.controller;

import java.io.IOException;
import java.util.List;

import com.javacart.model.Order;
import com.javacart.model.User;
import com.javacart.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/myOrders")
public class MyOrdersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService service = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("user") == null){

            response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        List<Order> orderList = service.getMyOrders(user.getUserId());

        request.setAttribute("orderList", orderList);

        request.getRequestDispatcher("/customer/myOrders.jsp")
               .forward(request, response);
    }
}
