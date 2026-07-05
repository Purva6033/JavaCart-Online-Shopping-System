package com.javacart.controller;

import java.io.IOException;
import java.util.List;

import com.javacart.model.Order;
import com.javacart.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adminOrders")
public class AdminOrdersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService service = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Order> orderList = service.getAllOrders();

        request.setAttribute("orderList", orderList);

        request.getRequestDispatcher("/admin/adminOrders.jsp")
               .forward(request, response);
    }
}
