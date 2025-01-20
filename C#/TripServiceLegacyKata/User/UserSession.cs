using TripServiceLegacyKata.Exception;

namespace TripServiceLegacyKata.User
{
    public class UserSession
    {
        private static readonly UserSession userSession = new UserSession();

        private UserSession() { }

        public static UserSession GetInstance()
        {
            return userSession;
        }

        public bool IsUserLoggedIn(User user)
        {
            throw new DependendClassCallDuringUnitTestException
                ("UserSession.IsUserLoggedIn() should not be called when running a unit test!");
        }

        public User GetLoggedUser()
        {
            throw new DependendClassCallDuringUnitTestException
                ("UserSession.GetLoggedUser() should not be called when running a unit test!");
        }
    }
}
