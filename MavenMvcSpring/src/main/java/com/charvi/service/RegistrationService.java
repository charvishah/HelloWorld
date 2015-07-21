package com.charvi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charvi.dao.UserDao;
import com.charvi.exception.DaoException;
import com.charvi.exception.ServiceException;

@Service
public class RegistrationService {


	@Autowired
	UserDao userDao;
	
	public boolean doesUserExist(String userName){
		
		try {
			String userFromDB = userDao.getUserName(userName);

			if (null != userFromDB) {
				if (userFromDB.equals(userName)) {
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

	public void registerUser(String username, String password) {
		
		try{
			
			userDao.registerUser(username,password);
		}
		catch (Exception ex) {
			if (!(ex instanceof DaoException)) {
				ex.printStackTrace();
			}
			throw new ServiceException();
		}
		}


}
