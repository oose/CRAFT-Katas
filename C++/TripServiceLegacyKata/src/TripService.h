#ifndef TRIPSERVICE_H_
#define TRIPSERVICE_H_

#include <list>
#include "TripServiceDependencies.h"

class TripService {
public:
  std::list<Trip> getTripsByUser(User user);

protected:
  virtual User* getLoggedUserFromSession() const;
  virtual std::list<Trip> findTripsByUser(User user) const;
};

#endif /* TRIPSERVICE_H_ */
