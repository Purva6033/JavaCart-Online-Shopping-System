package com.javacart.controller;

import java.io.IOException;

import com.javacart.service.CategoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteCategory")
public class DeleteCategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryService service = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int categoryId = Integer.parseInt(
                request.getParameter("id"));

        service.deleteCategory(categoryId);

        response.sendRedirect("categories");
    }
}
