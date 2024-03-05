package org.craftedsw.user;

import org.craftedsw.exceptions.DependentClassCallDuringUnitTestException;

public class UserSession {

	private static final UserSession userSession = new UserSession();
	
	private UserSession() {
	}
	
	public static UserSession getInstance() {
		return userSession;
	}

	public boolean isUserLoggedIn(User user) {
		throw new DependentClassCallDuringUnitTestException(
				"UserSession.isUserLoggedIn() should not be called in an unit test");
	}

	public User getLoggedUser() {
		throw new DependentClassCallDuringUnitTestException(
				"UserSession.getLoggedUser() should not be called in an unit test");
	}

}
