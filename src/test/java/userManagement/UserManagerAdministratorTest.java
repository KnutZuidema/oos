package userManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerAdministratorTest {
    private final String id = "id";
    private final char[] password = {'p', 'w'};
    private UserManagerAdministrator userAdmin;
    private User user1, user2;

    @BeforeEach
    void setUp() {
        userAdmin = new UserManagerAdministrator();
        user1 = new User();
        user2 = new User(id, password);
        try {
            userAdmin.addUser(user1);
        } catch (UserAlreadyExistsException ignored) {
        }
    }

    @Test
    void addUser() {
        assertDoesNotThrow(() -> userAdmin.addUser(user2));
        assertThrows(UserAlreadyExistsException.class, () -> userAdmin.addUser(user1));
    }

    @Test
    void verifyUser() {
        assertTrue(userAdmin.verifyUser(user1));
        assertFalse(userAdmin.verifyUser(user2));
    }

    @Test
    void removeUser() {
        userAdmin.removeUser(user1);
        assertFalse(userAdmin.verifyUser(user1));
    }
}