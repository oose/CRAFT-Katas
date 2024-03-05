package org.craftedsw.trip;

import java.util.List;

import org.craftedsw.exceptions.DependentClassCallDuringUnitTestException;
import org.craftedsw.user.User;



public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new DependentClassCallDuringUnitTestException(
				"TripDAO should not be invoked on an unit test.");
	}
	
	public List<Trip> findTripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}
	
}