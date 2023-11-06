#include "TripService.h"

std::list<Trip> TripService::getTripsByUser(User user)
{
  std::list<Trip> triplist;
  User* loggedUser = getLoggedUserFromSession();
  bool isFriend = false;
  if ( loggedUser )
  {
    std::list<User>::iterator i;
    for( i = user.GetFriends().begin(); i != user.GetFriends().end(); ++i )
    {
      if ( *i == *loggedUser )
      {
        isFriend = true;
        break;
      }
    }
    if (isFriend)
    {
      triplist = findTripsByUser(user);
    }
    return triplist;
  }
  else
  {
    throw "UserNotLoggedInException";
  }
}

User* TripService::getLoggedUserFromSession() const {
  return UserSession::GetInstance()->GetLoggedUser();
} 

std::list<Trip> TripService::findTripsByUser(User user) const{
  return TripDAO::FindTripsByUser(user);
}
