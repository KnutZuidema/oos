package userManagement;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Administrator class for a local user store
 */
public class UserManagerAdministrator implements UserManager, Serializable {

    private List<User> userStore;

    /**
     * default constructor for UserManagerAdministrator
     */
    public UserManagerAdministrator() {
        this.userStore = new LinkedList<>();
    }

    /**
     * Class method to initialize a UserManagerAdministrator from a previously
     * serialized one.
     *
     * @param fileName name of the serialized UserManagerAdministrator
     * @return UserManagerAdministrator instance
     * @throws IOException            if the file does not exist,
     *                                is a directory rather than a regular file,
     *                                or for some other reason cannot be opened for
     *                                reading.
     * @throws ClassNotFoundException if class of a serialized object cannot be
     *                                found.
     */
    public static UserManagerAdministrator deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream file_input = new FileInputStream(fileName);
        ObjectInputStream object_input = new ObjectInputStream(file_input);
        UserManagerAdministrator userManager = (UserManagerAdministrator) object_input.readObject();
        object_input.close();
        file_input.close();
        return userManager;
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

    /**
     * Serialize the UserAdministrator instance to a file
     *
     * @param fileName name of the file
     * @throws IOException if the file exists but is a directory
     *                     rather than a regular file, does not exist but cannot
     *                     be created, or cannot be opened for any other reason
     */
    public void serialize(String fileName) throws IOException {
        FileOutputStream file_output = new FileOutputStream(fileName);
        ObjectOutputStream object_output = new ObjectOutputStream(file_output);
        object_output.writeObject(this);
        object_output.close();
        file_output.close();
    }
}
