#include "TripServiceTestadapter.h"

void TripServiceTestadapter::setLoggedUser(UserPtr loggedUser) {
  _loggedUser = loggedUser;
}

UserPtr TripServiceTestadapter::getLoggedUserFromSession() const {
  return _loggedUser;
}

std::list<Trip> TripServiceTestadapter::findTripsByUser(User user) const {
  return _trips;
}

void TripServiceTestadapter::setFakedTripList(const std::list<Trip>& trips) {
  _trips = trips;
}
