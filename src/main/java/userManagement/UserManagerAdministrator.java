package userManagement;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Administrator class for a local user store
 */
public class UserManagerAdministrator implements UserManager, Serializable {
    public final static long serialVersionUID = 0xFFF;

    private String filename = null;
    private List<User> userStore;

    /**
     * Default constructor for UserManagerAdministrator
     * Future operations will only be in memory
     */
    public UserManagerAdministrator() {
        this.userStore = new LinkedList<>();
    }

    /**
     * Initialize persistent storage of UserManagerAdministrator and set filename of persistent storage.
     * Future operations will be synchronized with file on disk
     *
     * @param filename name of the file on disk
     * @throws IOException if the file does not exist,
     *                     is a directory rather than a regular file,
     *                     or for some other reason cannot be opened for
     *                     reading.
     */
    public UserManagerAdministrator(String filename) throws IOException {
        try {
            this.userStore = deserialize(filename).userStore;
        } catch (ClassNotFoundException | IOException e) {
            this.userStore = new LinkedList<>();
        }
        this.filename = filename;
        serialize(filename);
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
     *                                    if the file exists but is a directory
     *                                    rather than a regular file, does not exist but cannot
     *                                    be created, or cannot be opened for any other reason
     * @throws IOException                if the file does not exist,
     *                                    is a directory rather than a regular file,
     *                                    or for some other reason cannot be opened for
     *                                    reading.
     * @throws ClassNotFoundException     if class of a serialized object cannot be
     *                                    found.
     */
    @Override
    public void addUser(User user) throws UserAlreadyExistsException, IOException, ClassNotFoundException {
        if (this.filename != null) {
            this.userStore = deserialize(this.filename).userStore;
        }
        if (userStore.stream().anyMatch(localUser -> localUser.getId().equals(user.getId()))) {
            throw new UserAlreadyExistsException();
        }
        userStore.add(user);
        if (this.filename != null) {
            serialize(this.filename);
        }
    }

    /**
     * verify that a User exists in userStore
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
    @Override
    public boolean verifyUser(User user) throws IOException, ClassNotFoundException {
        if (this.filename != null) {
            this.userStore = deserialize(this.filename).userStore;
        }
        return userStore.stream().anyMatch(localUser -> localUser.equals(user));
    }

    /**
     * remove User with equal ID from userStore. If User ID does not exist in userStore,
     * it does nothing
     *
     * @param user User to be removed from userStore
     * @throws IOException            if the file does not exist,
     *                                is a directory rather than a regular file,
     *                                or for some other reason cannot be opened for
     *                                reading
     * @throws ClassNotFoundException if class of a serialized object cannot be
     *                                found.
     */
    public void removeUser(User user) throws IOException, ClassNotFoundException {
        if (this.filename != null) {
            this.userStore = deserialize(this.filename).userStore;
        }
        userStore.removeIf(localUser -> localUser.getId().equals(user.getId()));
        if (this.filename != null) {
            serialize(this.filename);
        }
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
