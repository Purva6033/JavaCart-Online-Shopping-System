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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("user") == null){

            response.sendRedirect(request.getContextPath()
                    + "/customer/login.jsp");

            return;

        }

        User sessionUser = (User)session.getAttribute("user");

        User user = service.getUserById(sessionUser.getUserId());

        request.setAttribute("user", user);

        request.getRequestDispatcher("/customer/profile.jsp")
               .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        User sessionUser =
                (User)session.getAttribute("user");

        User user = new User();

        user.setUserId(sessionUser.getUserId());
        user.setFullName(request.getParameter("fullName"));
        user.setPhone(request.getParameter("phone"));
        user.setAddress(request.getParameter("address"));

        boolean status = service.updateProfile(user);

        if(status){

            User updatedUser =
                    service.getUserById(sessionUser.getUserId());

            session.setAttribute("user", updatedUser);

            response.sendRedirect(request.getContextPath()
                    + "/profile?success=1");

        }else{

            response.sendRedirect(request.getContextPath()
                    + "/profile?error=1");

        }

    }

}