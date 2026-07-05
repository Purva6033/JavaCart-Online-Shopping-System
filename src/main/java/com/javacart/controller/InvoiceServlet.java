package com.javacart.controller;

import java.io.IOException;
import java.util.List;

import com.javacart.model.Order;
import com.javacart.model.OrderDetail;
import com.javacart.model.User;
import com.javacart.service.OrderService;
import com.javacart.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/invoice")
public class InvoiceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService orderService = new OrderService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("user")==null){

            response.sendRedirect(request.getContextPath()
                    + "/customer/login.jsp");

            return;

        }

        User sessionUser = (User)session.getAttribute("user");

        int orderId =
                Integer.parseInt(request.getParameter("orderId"));

        List<OrderDetail> orderDetails =
                orderService.getOrderDetails(orderId);

        List<Order> orderList =
                orderService.getMyOrders(sessionUser.getUserId());

        Order selectedOrder = null;

        for(Order order : orderList){

            if(order.getOrderId()==orderId){

                selectedOrder = order;
                break;

            }

        }

        User user =
                userService.getUserById(sessionUser.getUserId());

        request.setAttribute("user", user);
        request.setAttribute("order", selectedOrder);
        request.setAttribute("orderDetails", orderDetails);

        request.getRequestDispatcher("/customer/invoice.jsp")
               .forward(request, response);

    }

}
