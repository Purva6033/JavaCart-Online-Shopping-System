package com.javacart.controller;

import java.io.IOException;
import java.util.List;

import com.javacart.model.OrderDetail;
import com.javacart.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderDetails")
public class OrderDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService service = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        request.setAttribute("orderId", orderId);

        List<OrderDetail> detailList = service.getOrderDetails(orderId);

        request.setAttribute("detailList", detailList);
        request.setAttribute("orderId", orderId);

        request.getRequestDispatcher("/customer/orderDetails.jsp")
               .forward(request, response);
    }
}
