package com.javacart.controller;

import java.io.IOException;

import com.javacart.model.Admin;
import com.javacart.service.AdminService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adminlogin")
public class AdminLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AdminService service = new AdminService();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Admin admin = service.loginAdmin(email, password);

        if (admin != null) {

            request.getSession().setAttribute("admin", admin);

            // Redirect to servlet, NOT directly to JSP
            response.sendRedirect(request.getContextPath() + "/adminDashboard");

        } else {

            request.setAttribute("error", "Invalid Email or Password");

            request.getRequestDispatcher("/admin/login.jsp")
                   .forward(request, response);
        }
    }
}