package com.javacart.controller;

import java.io.IOException;

import com.javacart.model.Category;
import com.javacart.service.CategoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addCategory")
public class AddCategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryService service = new CategoryService();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String categoryName = request.getParameter("categoryName");

        Category category = new Category();
        category.setCategoryName(categoryName);

        service.addCategory(category);

        response.sendRedirect("categories");
    }
}