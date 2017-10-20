#ifndef TRIPSERVICE_H_
#define TRIPSERVICE_H_

#include <list>
#include "TripServiceDependencies.h"

class TripService {
public:
  std::list<Trip> getTripsByUser(User user);
};

#endif /* TRIPSERVICE_H_ */
