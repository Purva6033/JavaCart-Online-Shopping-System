package com.javacart.dao;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.javacart.model.Admin;
import com.javacart.model.RecentOrder;
import com.javacart.model.TopSellingProduct;
import com.javacart.utility.DBConnection;


public class AdminDAO {

    public Admin loginAdmin(String email, String password) {

        Admin admin = null;

        String query = "SELECT * FROM admin WHERE email=? AND password=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
        ) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                admin = new Admin();

                admin.setId(rs.getInt("id"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return admin;
    }
    public List<RecentOrder> getRecentOrders() {

        List<RecentOrder> orderList = new ArrayList<>();

        String sql =
            "SELECT o.order_id, " +
            "u.full_name, " +
            "o.total_amount, " +
            "o.order_status, " +
            "o.order_date " +
            "FROM orders o " +
            "INNER JOIN users u ON o.user_id=u.user_id " +
            "ORDER BY o.order_date DESC " +
            "LIMIT 5";

        try(
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ){

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                RecentOrder order = new RecentOrder();

                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerName(rs.getString("full_name"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setOrderDate(rs.getString("order_date"));

                orderList.add(order);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return orderList;

    }
    public double getTotalRevenue() {

        double revenue = 0;

        String sql = "SELECT IFNULL(SUM(total_amount),0) AS revenue FROM orders";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {

            if (rs.next()) {

                revenue = rs.getDouble("revenue");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return revenue;
    }
    public int getTotalProducts() {

        int total = 0;

        String sql = "SELECT COUNT(*) FROM products";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }
    public int getTotalCustomers() {

        int total = 0;

        String sql = "SELECT COUNT(*) FROM users";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }
    public int getTotalOrders() {

        int total = 0;

        String sql = "SELECT COUNT(*) FROM orders";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }
    public List<TopSellingProduct> getTopSellingProducts() {

        List<TopSellingProduct> list = new ArrayList<>();

        String sql =
                "SELECT p.product_name, SUM(od.quantity) AS total_sold " +
                "FROM products p " +
                "JOIN order_details od ON p.product_id = od.product_id " +
                "GROUP BY p.product_id, p.product_name " +
                "ORDER BY total_sold DESC " +
                "LIMIT 5";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {

            while (rs.next()) {

                TopSellingProduct product = new TopSellingProduct();

                product.setProductName(rs.getString("product_name"));
                product.setTotalSold(rs.getInt("total_sold"));

                // Debug
                System.out.println(product.getProductName() + " : " + product.getTotalSold());

                list.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}