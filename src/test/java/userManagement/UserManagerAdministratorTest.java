package userManagement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerAdministratorTest {
    private final String id = "id";
    private final char[] password = {'p', 'w'};
    private final String fileName1 = "userAdmin1.ser";
    private final String fileName2 = "userAdmin2.ser";
    private UserManagerAdministrator userAdmin;
    private User user1, user2;

    @BeforeEach
    void setUp() {
        userAdmin = new UserManagerAdministrator();
        user1 = new User();
        user2 = new User(id, password);
        try {
            userAdmin.addUser(user1);
            userAdmin.serialize(fileName1);
        } catch (UserAlreadyExistsException | IOException ignored) {
        }
    }

    @AfterEach
    void tearDown() {
        try {
            Files.deleteIfExists(Paths.get(fileName1));
            Files.deleteIfExists(Paths.get(fileName2));
        } catch (IOException ignored) {
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

    @Test
    void serialize() {
        assertDoesNotThrow(() -> userAdmin.serialize(fileName2));
        File file = new File(fileName2);
        assertTrue(file.exists());
    }

    @Test
    void deserialize() {
        try {
            UserManagerAdministrator userAdmin2 = UserManagerAdministrator.deserialize(fileName1);
            assertTrue(userAdmin2.verifyUser(user1));
        } catch (IOException | ClassNotFoundException e) {
            fail("Threw during deserialization");
        }
    }
}