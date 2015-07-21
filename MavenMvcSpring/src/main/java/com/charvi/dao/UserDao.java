package com.charvi.dao;

/*
 * This class is a Dao class which takes the req from the service class
 * and performs operation by taking a connection from the Db connection class.
 * 
 * 
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.charvi.exception.ServiceException;
import com.charvi.userInfo.User;
import com.charvi.util.DBConnection;

@Repository
public class UserDao {

	Connection con;
	PreparedStatement stmt;
	DBConnection dbConn = new DBConnection();

	public String getUserPassword(String userName) {

		String passwordFromDB = null;

		try {
			con = dbConn.getDBConnection();

			String sql = "SELECT user_password FROM  USER where user_name = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, userName);

			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				passwordFromDB = res.getString("USER_PASSWORD");
			}
			dbConn.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServiceException("global.exception.message");
		}
		return passwordFromDB;
	}

	public String getUserName(String userName) {

		String usernameFromDB = null;
		try {
			con = dbConn.getDBConnection();
			String sql = "SELECT user_name FROM  USER where user_name = ? ";
			stmt = con.prepareStatement(sql);
			
			// sets the user name dynamically at the first ? sign
			stmt.setString(1, userName); 

			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				// retrieves password from the result set
				usernameFromDB = res.getString("USER_NAME"); 
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServiceException();
		} finally {
			dbConn.closeConnection();
		}
		return usernameFromDB;
	}

	public void registerUser(String username, String password) {
		try {
			con = dbConn.getDBConnection();
			String sql = "INSERT INTO USER(USER_NAME, USER_PASSWORD) VALUES (?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username); // sets the user name dynamically at
											// the first ? sign
			stmt.setString(2, password); // sets the user name dynamically at
											// the first ? sign
			stmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServiceException();
		} finally {
			dbConn.closeConnection();
		}

	}
	
	/*
	 * Retrieve a list of users from the database 
	 * when the user logs in and displays all the users
	 */

	public List<User> getUsers() {

		List<User> users = new ArrayList<User>();
		try {
			con = dbConn.getDBConnection();
			String sql = "SELECT USER.user_id, USER.user_name, USER_ADDRESS.city FROM USER, USER_ADDRESS WHERE USER.USER_ID = USER_ADDRESS.USER_ID";

			stmt = con.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				users.add(new User(res.getInt("USER_ID"),res.getString("USER_NAME"), res.getString("CITY"))); 
				// retrieves password from the result set
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServiceException();
		} finally {
			dbConn.closeConnection();
		}
		return users;

	}
	
	/*
	 * Delete a user from a Database by getting its Userid
	 * when a Delete button is hit.
	 */

	public void deleteUserRecord(int userid) {
		
		try {
			con = dbConn.getDBConnection();
			String sql = "DELETE FROM ATHENA.USER WHERE USER_ID = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userid);
			stmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServiceException();
		} finally {
			dbConn.closeConnection();
		}
		
	}

}
