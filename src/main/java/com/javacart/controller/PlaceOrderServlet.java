package com.javacart.controller;

import java.io.IOException;
import java.util.List;

import com.javacart.model.CartItem;
import com.javacart.model.Order;
import com.javacart.model.Payment;
import com.javacart.model.User;
import com.javacart.service.CartService;
import com.javacart.service.OrderService;
import com.javacart.service.PaymentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartService cartService = new CartService();
    private OrderService orderService = new OrderService();
    private PaymentService paymentService = new PaymentService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("user")==null){

            response.sendRedirect(request.getContextPath()+"/customer/login.jsp");
            return;

        }

        User user = (User) session.getAttribute("user");

        List<CartItem> cartList =
                cartService.getCartItems(user.getUserId());

        double total = 0;

        for(CartItem item : cartList){

            total += item.getPrice() * item.getQuantity();

        }

        Order order = new Order();

        order.setUserId(user.getUserId());
        order.setTotalAmount(total);
        order.setOrderStatus("Pending");

        int orderId = orderService.createOrder(order);

        if(orderId > 0){

            // Save Order Details
            for(CartItem item : cartList){

                orderService.addOrderDetail(
                        orderId,
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice());

            }

            // Save Payment
            Payment payment =
                    (Payment)session.getAttribute("payment");

            if(payment != null){

                payment.setOrderId(orderId);

                paymentService.savePayment(payment);

                session.removeAttribute("payment");

            }

            // Clear Cart
            orderService.clearCart(user.getUserId());

            // Success
            response.sendRedirect(request.getContextPath()
                    + "/customer/orderSuccess.jsp");

        }
        else{

            response.getWriter().println("Order Failed");

        }

    }

}