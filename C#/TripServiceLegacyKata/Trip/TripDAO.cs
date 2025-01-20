using System.Collections.Generic;
using TripServiceLegacyKata.Exception;

namespace TripServiceLegacyKata.Trip
{
    public class TripDAO
    {
        public static List<Trip> FindTripsByUser(User.User user)
        {
            throw new DependendClassCallDuringUnitTestException
                ("TripDAO should not be called when running a unit test!");
        }
    }
}
