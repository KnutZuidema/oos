package userManagement;

import java.io.Serializable;
import java.util.Arrays;

/**
 * User class to store id and password of a user
 */
public class User implements Serializable {
    public final static long serialVersionUID = 0xFF;
    private String id;
    private char[] password;

    /**
     * Default constructor for User
     */
    public User() {
        this.id = "";
        this.password = new char[0];
    }

    /**
     * Constructor for User
     *
     * @param id       id for the User
     * @param password password for the User
     */
    public User(String id, char[] password) {
        this.id = id;
        this.password = password;
    }

    /**
     * Getter for id
     *
     * @return value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for password
     *
     * @return value of password
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * toString implementation of User
     *
     * @return String representation of User
     */
    @Override
    public String toString() {
        return String.format("User(id=%s, password=%s)", this.id, String.copyValueOf(this.password));
    }

    /**
     * equals implementation of User
     *
     * @param obj object to be compared
     * @return True of id and password of object are equal to User
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return this.id.equals(user.id) && Arrays.equals(this.password, user.password);
    }
}
