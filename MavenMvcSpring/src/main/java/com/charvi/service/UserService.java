package com.charvi.service;

import java.util.List;

import com.charvi.dao.UserDao;
import com.charvi.exception.DaoException;
import com.charvi.exception.ServiceException;
import com.charvi.userInfo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	/*
	 * Takes the req from the Login Controller 
	 * and checks for the validity of the 
	 * user password
	 * 
	 */
	public boolean isValidPassword(String userName, String password) {
		try {
	
			String pwdFromDB = userDao.getUserPassword(userName);

			if (null != pwdFromDB) {
				if (pwdFromDB.equals(password)) {
					return true;
				}
			}
		} catch (Exception ex) {
			if (!(ex instanceof DaoException)) {
				ex.printStackTrace();
			}
			throw new ServiceException();
		}
		return false;
	}
	
	/*
	 * To retrieve list of users
	 * 
	 */
	
	public List<User> getUsers(){
		
		List<User> users = userDao.getUsers();
		return users;
	}
	
	

	public void deleteUser(int userid) {
		userDao.deleteUserRecord(userid);
		
	}

}
