package com.javacart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.javacart.model.Order;
import com.javacart.model.OrderDetail;
import com.javacart.utility.DBConnection;


public class OrderDAO {
	public int createOrder(Order order) {

	    int orderId = 0;

	    String query = "INSERT INTO orders(user_id,total_amount,order_status) VALUES(?,?,?)";

	    try (
	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps =
	        con.prepareStatement(query,
	        PreparedStatement.RETURN_GENERATED_KEYS);
	    ) {

	        ps.setInt(1, order.getUserId());
	        ps.setDouble(2, order.getTotalAmount());
	        ps.setString(3, order.getOrderStatus());

	        ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();

	        if (rs.next()) {

	            orderId = rs.getInt(1);

	        }

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	    return orderId;

	}
	
	public boolean addOrderDetail(int orderId,
            int productId,
            int quantity,
            double price) {

boolean status = false;

String query = "INSERT INTO order_details(order_id, product_id, quantity, price) VALUES(?,?,?,?)";

try (
Connection con = DBConnection.getConnection();
PreparedStatement ps = con.prepareStatement(query);
) {

ps.setInt(1, orderId);
ps.setInt(2, productId);
ps.setInt(3, quantity);
ps.setDouble(4, price);

int rows = ps.executeUpdate();

if (rows > 0) {
status = true;
}

} catch (Exception e) {
e.printStackTrace();
}

return status;
}
	public boolean clearCart(int userId) {

	    boolean status = false;

	    String query = "DELETE FROM cart WHERE user_id=?";

	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(query);
	    ) {

	        ps.setInt(1, userId);

	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            status = true;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return status;
	}
	public List<Order> getMyOrders(int userId) {

	    List<Order> orderList = new ArrayList<>();

	    String query = "SELECT * FROM orders WHERE user_id=? ORDER BY order_date DESC";

	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(query);
	    ) {

	        ps.setInt(1, userId);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            Order order = new Order();

	            order.setOrderId(rs.getInt("order_id"));
	            order.setUserId(rs.getInt("user_id"));
	            order.setTotalAmount(rs.getDouble("total_amount"));
	            order.setOrderStatus(rs.getString("order_status"));
	            order.setOrderDate(rs.getTimestamp("order_date"));

	            orderList.add(order);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return orderList;
	}
	public List<OrderDetail> getOrderDetails(int orderId){

	    List<OrderDetail> detailList = new ArrayList<>();

	    String sql =
	            "SELECT od.order_detail_id, od.order_id, od.product_id, " +
	            "od.quantity, od.price, " +
	            "p.product_name, p.image_url " +
	            "FROM order_details od " +
	            "JOIN products p ON od.product_id = p.product_id " +
	            "WHERE od.order_id=?";

	    try{

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, orderId);

	        ResultSet rs = ps.executeQuery();

	        while(rs.next()){

	            OrderDetail detail = new OrderDetail();

	            detail.setOrderDetailId(rs.getInt("order_detail_id"));
	            detail.setOrderId(rs.getInt("order_id"));
	            detail.setProductId(rs.getInt("product_id"));
	            detail.setQuantity(rs.getInt("quantity"));
	            detail.setPrice(rs.getDouble("price"));

	            detail.setProductName(rs.getString("product_name"));
	            detail.setImageUrl(rs.getString("image_url"));

	            detailList.add(detail);

	        }

	    }catch(Exception e){

	        e.printStackTrace();

	    }

	    return detailList;

	}
	public List<Order> getAllOrders() {

	    List<Order> orderList = new ArrayList<>();

	    String sql = "SELECT * FROM orders ORDER BY order_date DESC";

	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	    ) {

	        while (rs.next()) {

	            Order order = new Order();

	            order.setOrderId(rs.getInt("order_id"));
	            order.setUserId(rs.getInt("user_id"));
	            order.setTotalAmount(rs.getDouble("total_amount"));
	            order.setOrderStatus(rs.getString("order_status"));
	            order.setOrderDate(rs.getTimestamp("order_date"));

	            orderList.add(order);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return orderList;
	}
	public boolean updateOrderStatus(int orderId, String status) {

	    boolean result = false;

	    String sql = "UPDATE orders SET order_status=? WHERE order_id=?";

	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	    ) {

	        ps.setString(1, status);
	        ps.setInt(2, orderId);

	        result = ps.executeUpdate() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}
}
