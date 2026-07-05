package com.javacart.controller;

import java.io.IOException;
import java.util.List;

import com.javacart.model.User;
import com.javacart.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<User> userList = service.getAllUsers();

        request.setAttribute("userList", userList);

        request.getRequestDispatcher("/admin/customers.jsp")
               .forward(request, response);
    }
}