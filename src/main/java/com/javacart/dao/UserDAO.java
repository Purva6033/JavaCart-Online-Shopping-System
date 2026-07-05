package com.javacart.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import com.javacart.model.User;
import com.javacart.utility.DBConnection;

public class UserDAO {

	public boolean saveUser(User user) 
	{

	    boolean status = false;

	    String query = "INSERT INTO users(full_name, email, password, phone, address) VALUES (?, ?, ?, ?, ?)";

	    Connection con = DBConnection.getConnection();

	    if (con == null) {
	        throw new RuntimeException("Database connection is NULL. Check DBConnection.getConnection().");
	    }

	    System.out.println("Connection = " + con);

	    try {

	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setString(1, user.getFullName());
	        ps.setString(2, user.getEmail());
	        ps.setString(3, user.getPassword());
	        ps.setString(4, user.getPhone());
	        ps.setString(5, user.getAddress());

	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            status = true;
	        }

	        ps.close();
	        con.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return status;
	}
	public User loginUser(String email, String password) {

	    User user = null;

	    String query = "SELECT * FROM users WHERE email=? AND password=?";

	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(query);
	    ) {

	        ps.setString(1, email);
	        ps.setString(2, password);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            user = new User();

	            user.setUserId(rs.getInt("user_id"));
	            user.setFullName(rs.getString("full_name"));
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            user.setPhone(rs.getString("phone"));
	            user.setAddress(rs.getString("address"));
	            user.setCreatedAt(rs.getTimestamp("created_at"));

	            // Debug
	            System.out.println("User Name = " + user.getFullName());
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return user;
	}
	public User getUserById(int userId) {

	    User user = null;

	    String sql = "SELECT * FROM users WHERE user_id=?";

	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	    ) {

	        ps.setInt(1, userId);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            user = new User();

	            user.setUserId(rs.getInt("user_id"));
	            user.setFullName(rs.getString("full_name"));
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            user.setPhone(rs.getString("phone"));
	            user.setAddress(rs.getString("address"));
	            user.setCreatedAt(rs.getTimestamp("created_at"));

	        }

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	    return user;
	}public boolean updateProfile(User user) {

	    boolean status = false;

	    String sql =
	            "UPDATE users SET full_name=?, phone=?, address=? WHERE user_id=?";

	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	    ) {

	        ps.setString(1, user.getFullName());
	        ps.setString(2, user.getPhone());
	        ps.setString(3, user.getAddress());
	        ps.setInt(4, user.getUserId());

	        status = ps.executeUpdate() > 0;

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	    return status;
	}
	public boolean changePassword(int userId,
            String currentPassword,
            String newPassword) {

boolean status = false;

String sql =
"UPDATE users SET password=? WHERE user_id=? AND password=?";

try (
Connection con = DBConnection.getConnection();
PreparedStatement ps = con.prepareStatement(sql);
) {

ps.setString(1, newPassword);
ps.setInt(2, userId);
ps.setString(3, currentPassword);

status = ps.executeUpdate() > 0;

} catch (Exception e) {

e.printStackTrace();

}

return status;
}
	public List<User> getAllUsers() {

	    List<User> userList = new ArrayList<>();

	    String sql = "SELECT * FROM users ORDER BY user_id DESC";

	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	    ) {

	        while (rs.next()) {

	            User user = new User();

	            user.setUserId(rs.getInt("user_id"));
	            user.setFullName(rs.getString("full_name"));
	            user.setEmail(rs.getString("email"));
	            user.setPhone(rs.getString("phone"));
	            user.setAddress(rs.getString("address"));

	            userList.add(user);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return userList;
	}


}