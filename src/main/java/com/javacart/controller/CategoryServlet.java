package com.javacart.controller;

import java.io.IOException;
import java.util.List;

import com.javacart.model.Category;
import com.javacart.service.CategoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryService service = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Category> categoryList = service.getAllCategories();

        request.setAttribute("categoryList", categoryList);

        request.getRequestDispatcher("/admin/categories.jsp")
               .forward(request, response);
    }
}