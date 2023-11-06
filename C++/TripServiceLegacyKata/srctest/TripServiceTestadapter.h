#ifndef TRIPSERVICETESTADAPTER_H_
#define TRIPSERVICETESTADAPTER_H_

#include "../src/TripService.h"

class TripServiceTestadapter: public TripService {
public:
  void setLoggedUser(User* loggedUser);
  void setFakedTripList(const std::list<Trip>& trips);

protected:
  User* getLoggedUserFromSession() const override;
  std::list<Trip> findTripsByUser(User user) const override;

private:
  User* _loggedUser { nullptr };
  std::list<Trip> _trips;
};

#endif /* TRIPSERVICETESTADAPTER_H_ */
