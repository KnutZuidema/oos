package userManagement;

/**
 * Interface for basic user management operations
 */
public interface UserManager {

    /**
     * Add a user to the local store of users
     *
     * @param user User instance to be added
     * @throws UserAlreadyExistsException when User with same id already exists in local store
     */
    public void addUser(User user) throws UserAlreadyExistsException;

    /**
     * Verify that a User exists in local store
     *
     * @param user User to be verified
     * @return true if user exists, false if not
     */
    public boolean verifyUser(User user);
}
