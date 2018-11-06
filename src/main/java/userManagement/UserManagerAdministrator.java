package userManagement;

import java.util.LinkedList;
import java.util.List;

/**
 * Administrator class for a local user store
 */
public class UserManagerAdministrator implements UserManager {

    private List<User> userStore;

    /**
     * default constructor for UserManagerAdministrator
     */
    public UserManagerAdministrator() {
        this.userStore = new LinkedList<>();
    }

    /**
     * add User with unique id to userStore
     *
     * @param user User instance to be added
     * @throws UserAlreadyExistsException when User with same id already exists in local store
     */
    @Override
    public void addUser(User user) throws UserAlreadyExistsException {
        if (userStore.stream().anyMatch(localUser -> localUser.getId().equals(user.getId()))) {
            throw new UserAlreadyExistsException();
        }
        userStore.add(user);
    }

    /**
     * verify that a User exists in userStore
     *
     * @param user User to be verified
     * @return true if user exists, false if not
     */
    @Override
    public boolean verifyUser(User user) {
        return userStore.stream().anyMatch(localUser -> localUser.equals(user));
    }

    /**
     * remove User with equal ID from userStore. If User ID does not exist in userStore, it does nothing
     *
     * @param user User to be removed from userStore
     */
    public void removeUser(User user) {
        userStore.removeIf(localUser -> localUser.getId().equals(user.getId()));
    }
}
