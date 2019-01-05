package userManagement;

/**
 * Interface for basic user management operations
 */
public interface UserManager {

    /**
     * Add a user to the local store of users
     *
     * @param user User instance to be added
     */
    void addUser(User user) throws Exception;

    /**
     * Verify that a User exists in local store
     *
     * @param user User to be verified
     * @return true if user exists, false if not
     */
    boolean verifyUser(User user) throws Exception;
}
