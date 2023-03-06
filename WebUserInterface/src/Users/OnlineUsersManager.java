package Users;

import java.util.*;

public class OnlineUsersManager {

    private final Set<String> m_usersSet;

    public OnlineUsersManager() {
        m_usersSet = new HashSet<>();
    }

    public synchronized void addUser(String username) {
        m_usersSet.add(username);
    }

    public synchronized void removeUser(String username) {
        m_usersSet.remove(username);
    }

    public synchronized Set<String> getUsers() {
        return Collections.unmodifiableSet(m_usersSet);
    }

    public boolean isUserExists(String username) {
        return m_usersSet.contains(username);
    }
}