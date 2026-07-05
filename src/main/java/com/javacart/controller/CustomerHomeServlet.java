package com.javacart.controller;

import java.io.IOException;
import java.util.List;

import com.javacart.model.Category;
import com.javacart.model.Product;
import com.javacart.model.User;
import com.javacart.service.CategoryService;
import com.javacart.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/customerHome")
public class CustomerHomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();

  
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        String category = request.getParameter("category");
        String keyword = request.getParameter("keyword");

       
        int page = 1;
        int limit = 12;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int start = (page - 1) * limit;

        List<Product> productList;
        int totalProducts;

       
        if (keyword != null && !keyword.trim().isEmpty()) {

            productList = productService.searchProducts(keyword);
            totalProducts = productList.size();

        }
       
        else if (category != null &&
                !category.trim().isEmpty() &&
                !category.equalsIgnoreCase("All")) {

            productList = productService.getProductsByCategory(category);
            totalProducts = productList.size();

        }
        
        else {

            productList = productService.getProductsByPage(start, limit);
            totalProducts = productService.getProductCount();

        }

        List<Category> categoryList = categoryService.getAllCategories();

        int totalPages = (int)Math.ceil((double)totalProducts / limit);

        request.setAttribute("user", user);
        request.setAttribute("productList", productList);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("selectedCategory",
                category == null ? "All" : category);

        request.setAttribute("keyword", keyword);

        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/customer/home.jsp")
               .forward(request, response);
    }
}