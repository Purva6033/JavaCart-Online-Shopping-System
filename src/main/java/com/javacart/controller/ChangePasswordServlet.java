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

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("user") == null){

            response.sendRedirect(request.getContextPath()
                    + "/customer/login.jsp");

            return;
        }

        User user = (User)session.getAttribute("user");

        String currentPassword =
                request.getParameter("currentPassword");

        String newPassword =
                request.getParameter("newPassword");

        String confirmPassword =
                request.getParameter("confirmPassword");

        if(!newPassword.equals(confirmPassword)){

            response.sendRedirect(request.getContextPath()
                    + "/profile?passwordError=Passwords do not match");

            return;
        }

        boolean status =
                service.changePassword(
                        user.getUserId(),
                        currentPassword,
                        newPassword);

        if(status){

            response.sendRedirect(request.getContextPath()
                    + "/profile?passwordSuccess=Password Changed Successfully");

        }else{

            response.sendRedirect(request.getContextPath()
                    + "/profile?passwordError=Current Password Incorrect");

        }

    }

}