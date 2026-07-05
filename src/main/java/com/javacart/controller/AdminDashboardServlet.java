package com.javacart.controller;
import com.javacart.model.TopSellingProduct;
import java.io.IOException;

import com.javacart.model.Admin;
import com.javacart.service.AdminService;
import java.util.List;
import com.javacart.model.RecentOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("admin") == null) {

            response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
            return;
        }

        Admin admin = (Admin) session.getAttribute("admin");

        int totalProducts = adminService.getTotalProducts();
        int totalCustomers = adminService.getTotalCustomers();
        int totalOrders = adminService.getTotalOrders();
        double totalRevenue = adminService.getTotalRevenue();

        List<TopSellingProduct> topProducts =  adminService.getTopSellingProducts();
        
        request.setAttribute("topProducts", topProducts);
        List<RecentOrder> recentOrders = adminService.getRecentOrders();

        request.setAttribute("admin", admin);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("totalCustomers", totalCustomers);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("totalRevenue", totalRevenue);
        request.setAttribute("recentOrders", recentOrders);

        request.getRequestDispatcher("/admin/dashboard.jsp")
               .forward(request, response);
    }
    
}