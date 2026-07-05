package com.javacart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.javacart.model.Cart;
import com.javacart.utility.DBConnection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.javacart.model.CartItem;


public class CartDAO {

	public boolean addToCart(Cart cart) {

	    boolean status = false;

	    try {

	        Connection con = DBConnection.getConnection();

	        // Step 1 : Check if product already exists
	        String checkQuery =
	                "SELECT quantity FROM cart WHERE user_id=? AND product_id=?";

	        PreparedStatement checkPs = con.prepareStatement(checkQuery);

	        checkPs.setInt(1, cart.getUserId());
	        checkPs.setInt(2, cart.getProductId());

	        ResultSet rs = checkPs.executeQuery();

	        if(rs.next()) {

	            // Product already exists -> Increase quantity
	            String updateQuery =
	                    "UPDATE cart SET quantity = quantity + 1 " +
	                    "WHERE user_id=? AND product_id=?";

	            PreparedStatement updatePs =
	                    con.prepareStatement(updateQuery);

	            updatePs.setInt(1, cart.getUserId());
	            updatePs.setInt(2, cart.getProductId());

	            status = updatePs.executeUpdate() > 0;

	        } else {

	            // Product not in cart -> Insert new row
	            String insertQuery =
	                    "INSERT INTO cart(user_id,product_id,quantity) VALUES(?,?,?)";

	            PreparedStatement insertPs =
	                    con.prepareStatement(insertQuery);

	            insertPs.setInt(1, cart.getUserId());
	            insertPs.setInt(2, cart.getProductId());
	            insertPs.setInt(3, cart.getQuantity());

	            status = insertPs.executeUpdate() > 0;
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return status;
	}
    public List<CartItem> getCartItems(int userId) {

        List<CartItem> cartList = new ArrayList<>();

        String sql =
                "SELECT c.cart_id, c.quantity, " +
                "p.product_id, p.product_name, p.price, p.image_url " +
                "FROM cart c " +
                "JOIN products p ON c.product_id = p.product_id " +
                "WHERE c.user_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                CartItem item = new CartItem();

                item.setCartId(rs.getInt("cart_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setProductName(rs.getString("product_name"));
                item.setPrice(rs.getDouble("price"));
                item.setImageUrl(rs.getString("image_url"));
                item.setQuantity(rs.getInt("quantity"));

                cartList.add(item);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return cartList;
    }
    public boolean removeCartItem(int cartId) {

        boolean status = false;

        String sql = "DELETE FROM cart WHERE cart_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, cartId);

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    public boolean increaseQuantity(int cartId){

        boolean status = false;

        String sql =
            "UPDATE cart SET quantity = quantity + 1 WHERE cart_id=?";

        try{

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, cartId);

            status = ps.executeUpdate() > 0;

        }catch(Exception e){

            e.printStackTrace();

        }

        return status;

    }
    public boolean decreaseQuantity(int cartId){

        boolean status = false;

        String sql =
            "UPDATE cart SET quantity = quantity - 1 WHERE cart_id=? AND quantity>1";

        try{

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, cartId);

            status = ps.executeUpdate() > 0;

        }catch(Exception e){

            e.printStackTrace();

        }

        return status;

    }
    public int getCartCount(int userId) {

        int count = 0;

        String sql = "SELECT SUM(quantity) FROM cart WHERE user_id=?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                count = rs.getInt(1);

            }

        } catch(Exception e) {

            e.printStackTrace();

        }

        return count;
    }
}