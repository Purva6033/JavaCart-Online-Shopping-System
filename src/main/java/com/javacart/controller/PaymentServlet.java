package com.javacart.controller;

import java.io.IOException;

import com.javacart.model.Payment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String cardName = request.getParameter("cardName");
        String cardNumber = request.getParameter("cardNumber");
        String expiry = request.getParameter("expiry");
        String cvv = request.getParameter("cvv");

        // Debug (remove later)
        System.out.println("========== PAYMENT ==========");
        System.out.println("Card Name   : " + cardName);
        System.out.println("Card Number : " + cardNumber);
        System.out.println("Expiry      : " + expiry);
        System.out.println("CVV         : " + cvv);
        System.out.println("=============================");

        // Validate all fields
        if (cardName == null || cardName.trim().isEmpty()
                || cardNumber == null || !cardNumber.trim().matches("\\d{16}")
                || expiry == null || expiry.trim().isEmpty()
                || cvv == null || !cvv.trim().matches("\\d{3}")) {

            response.sendRedirect(request.getContextPath()
                    + "/customer/payment.jsp?error=Invalid Payment Details");
            return;
        }

        // Create Payment Object
        Payment payment = new Payment();
        payment.setCardHolderName(cardName.trim());
        payment.setCardNumber(cardNumber.trim());
        payment.setExpiryDate(expiry.trim());
        payment.setPaymentStatus("SUCCESS");

        // Store payment temporarily in session
        session.setAttribute("payment", payment);

        // Redirect to PlaceOrderServlet
        response.sendRedirect(request.getContextPath() + "/placeOrder");
    }
}