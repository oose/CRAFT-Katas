#ifndef TRIPSERVICETESTADAPTER_H_
#define TRIPSERVICETESTADAPTER_H_

#include "../src/TripService.h"

class TripServiceTestadapter: public TripService {
public:
  void setLoggedUser(UserPtr loggedUser);
  void setFakedTripList(const std::list<Trip>& trips);

protected:
  UserPtr getLoggedUserFromSession() const override;
  std::list<Trip> findTripsByUser(User user) const override;

private:
  UserPtr _loggedUser { nullptr };
  std::list<Trip> _trips;
};

#endif /* TRIPSERVICETESTADAPTER_H_ */
