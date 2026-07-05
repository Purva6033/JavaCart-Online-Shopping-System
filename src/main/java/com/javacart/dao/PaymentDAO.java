package com.javacart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.javacart.model.Payment;
import com.javacart.utility.DBConnection;

public class PaymentDAO {

    public boolean savePayment(Payment payment) {

        boolean status = false;

        String sql = "INSERT INTO payments(order_id, card_holder_name, card_number, expiry_date, payment_status) VALUES(?,?,?,?,?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ps.setInt(1, payment.getOrderId());
            ps.setString(2, payment.getCardHolderName());
            ps.setString(3, payment.getCardNumber());
            ps.setString(4, payment.getExpiryDate());
            ps.setString(5, payment.getPaymentStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

}
