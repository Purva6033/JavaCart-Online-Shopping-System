package com.javacart.service;

import java.util.List;

import com.javacart.dao.OrderDAO;
import com.javacart.model.Order;
import com.javacart.model.OrderDetail;

public class OrderService {

    private OrderDAO dao = new OrderDAO();

    public int createOrder(Order order) {

        return dao.createOrder(order);

    }

    public boolean addOrderDetail(int orderId,
                                  int productId,
                                  int quantity,
                                  double price) {

        return dao.addOrderDetail(orderId,
                                  productId,
                                  quantity,
                                  price);

    }

    public boolean clearCart(int userId) {

        return dao.clearCart(userId);

    }

    public List<Order> getMyOrders(int userId) {

        return dao.getMyOrders(userId);

    }
    public List<OrderDetail> getOrderDetails(int orderId) {

        return dao.getOrderDetails(orderId);

    }
    public List<Order> getAllOrders() {

        return dao.getAllOrders();

    }

    public boolean updateOrderStatus(int orderId, String status) {

        return dao.updateOrderStatus(orderId, status);

    }
}
