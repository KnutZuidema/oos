package userManagement;

import java.io.IOException;

/**
 * Interface for basic user management operations
 */
public interface UserManager {

    /**
     * Add a user to the local store of users
     *
     * @param user User instance to be added
     * @throws UserAlreadyExistsException when User with same id already exists in local store
     * @throws IOException                if the file does not exist,
     *                                    is a directory rather than a regular file,
     *                                    or for some other reason cannot be opened for
     *                                    reading.
     * @throws ClassNotFoundException     if class of a serialized object cannot be
     *                                    found.
     */
    void addUser(User user) throws UserAlreadyExistsException, IOException, ClassNotFoundException;

    /**
     * Verify that a User exists in local store
     *
     * @param user User to be verified
     * @return true if user exists, false if not
     * @throws IOException            if the file does not exist,
     *                                is a directory rather than a regular file,
     *                                or for some other reason cannot be opened for
     *                                reading.
     * @throws ClassNotFoundException if class of a serialized object cannot be
     *                                found.
     */
    boolean verifyUser(User user) throws IOException, ClassNotFoundException;
}
