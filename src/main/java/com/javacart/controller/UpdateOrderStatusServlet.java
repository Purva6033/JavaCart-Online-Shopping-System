package com.javacart.controller;

import java.io.IOException;

import com.javacart.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateOrderStatus")
public class UpdateOrderStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService service = new OrderService();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));

        String status = request.getParameter("status");

        service.updateOrderStatus(orderId, status);

        response.sendRedirect(request.getContextPath() + "/adminOrders");
    }
}
