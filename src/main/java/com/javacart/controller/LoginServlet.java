package com.javacart.controller;

import java.io.IOException;

import com.javacart.model.User;
import com.javacart.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        
        UserService service = new UserService();
        User user = service.loginUser(email, password);

        if(user != null){

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect(request.getContextPath() + "/customerHome");

        } else {

            
        	response.sendRedirect(request.getContextPath() + "/customerHome");

        }
    }
}