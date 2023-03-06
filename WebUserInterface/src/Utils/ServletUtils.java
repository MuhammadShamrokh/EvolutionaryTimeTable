package Utils;

import Constants.Constants;
import Users.PermUserManager;
import Users.TimeTableHostManager;
import Users.OnlineUsersManager;

import javax.servlet.ServletContext;

public class ServletUtils {
     private static final Object m_OnlineUserManagerLock =new Object();
     private static final Object m_PermUserManagerLock=new Object();
     private static final Object m_TimeTableHostManagerLock=new Object();



    public static OnlineUsersManager getOnlineUserManager(ServletContext i_ServletContext)
    {
        synchronized (m_OnlineUserManagerLock) {
            if (i_ServletContext.getAttribute(Constants.ONLINE_USER_MANAGER) == null) {
                i_ServletContext.setAttribute(Constants.ONLINE_USER_MANAGER,new OnlineUsersManager());
            }
        }
        return (OnlineUsersManager) i_ServletContext.getAttribute(Constants.ONLINE_USER_MANAGER);

    }

    public static PermUserManager getPermUserManager(ServletContext i_ServletContext)
    {
        synchronized (m_PermUserManagerLock) {
            if (i_ServletContext.getAttribute(Constants.PERM_USER_MANAGER) == null) {
                i_ServletContext.setAttribute(Constants.PERM_USER_MANAGER,new PermUserManager());
            }
        }
        return (PermUserManager) i_ServletContext.getAttribute(Constants.PERM_USER_MANAGER);

    }


    public static TimeTableHostManager getTimeTableInstances(ServletContext i_ServletContext)
    {
        synchronized (m_TimeTableHostManagerLock) {
            if (i_ServletContext.getAttribute(Constants.TIMETABLE_HOST_MANAGER) == null) {
                i_ServletContext.setAttribute(Constants.TIMETABLE_HOST_MANAGER,new TimeTableHostManager());
            }
        }
        return (TimeTableHostManager) i_ServletContext.getAttribute(Constants.TIMETABLE_HOST_MANAGER);

    }
}
