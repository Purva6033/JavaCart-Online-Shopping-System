package com.javacart.service;
import java.util.List;
import com.javacart.model.RecentOrder;
import com.javacart.model.TopSellingProduct;
import com.javacart.dao.AdminDAO;
import com.javacart.model.Admin;

public class AdminService {

    AdminDAO dao = new AdminDAO();

    public Admin loginAdmin(String email, String password) {

        return dao.loginAdmin(email, password);

    }
    public int getTotalProducts() {

        return dao.getTotalProducts();

    }

    public int getTotalCustomers() {

        return dao.getTotalCustomers();

    }

    public int getTotalOrders() {

        return dao.getTotalOrders();

    }
    public List<RecentOrder> getRecentOrders(){

        return dao.getRecentOrders();

    }
    public double getTotalRevenue() {

        return dao.getTotalRevenue();

    }
    public List<TopSellingProduct> getTopSellingProducts() {

        return dao.getTopSellingProducts();

    }
    

}