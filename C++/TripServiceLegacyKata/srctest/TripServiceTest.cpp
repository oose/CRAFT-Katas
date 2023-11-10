#include <gtest/gtest.h>
#include "TripServiceTestadapter.h"

int main(int argc, char **argv) {
  testing::InitGoogleTest(&argc, argv);
  return RUN_ALL_TESTS();
}

TEST (TripServiceTest, throwsExceptionIfUserIsNotLoggedIn) {
  TripServiceTestadapter tripService { };
  User anyUser(0);
  ASSERT_THROW(tripService.getTripsByUser(anyUser), const char*);
}

TEST (TripServiceTest, usersAreNotFriendsThenReturnedTripListIsEmpty) {
  TripServiceTestadapter tripService { };
  User aUser(0);
  User aLoggedUser(1);
  tripService.setLoggedUser(&aLoggedUser);
  ASSERT_TRUE(tripService.getTripsByUser(aUser).empty());
}

TEST (TripServiceTest, usersAreFriendsThenReturnedTripListIsFilled) {
  TripServiceTestadapter tripService { };

  User aUser(0);
  User aLoggedUser(1);
  aUser.AddFriend(aLoggedUser);
  tripService.setLoggedUser(&aLoggedUser);

  std::list<Trip> trips;
  trips.push_back(Trip());
  tripService.setFakedTripList(trips);

  ASSERT_FALSE(tripService.getTripsByUser(aUser).empty());
}
