package com.javacart.service;

import com.javacart.dao.PaymentDAO;
import com.javacart.model.Payment;

public class PaymentService {

    private PaymentDAO dao = new PaymentDAO();

    public boolean savePayment(Payment payment) {

        return dao.savePayment(payment);

    }

}
