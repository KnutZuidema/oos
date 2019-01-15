package remote;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import userManagement.User;
import userManagement.UserAlreadyExistsException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RemoteUserAdminMemoryTest {
    private final String id = "id";
    private final char[] password = {'p', 'w'};
    private RemoteUserAdmin userAdmin;
    private User user1, user2;


    @BeforeAll
    static void setUpAll() {
        Thread server = new Thread(() -> {
            try {
                new Server().start();
            } catch (IOException ignored) {
            }
        });
        server.setName("Server");
        server.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            fail(e);
        }
    }

    @BeforeEach
    void setUp() {
        user1 = new User();
        user2 = new User(id, password);
        try {
            userAdmin = new RemoteUserAdmin();
            userAdmin.addUser(user1);
        } catch (UserAlreadyExistsException | IOException | ClassNotFoundException e) {
            fail(e);
        }
    }

    @Test
    void addUser() {
        assertDoesNotThrow(() -> userAdmin.addUser(user2));
        try {
            assertTrue(userAdmin.verifyUser(user2));
        } catch (IOException | ClassNotFoundException e) {
            fail(e);
        }
        assertThrows(UserAlreadyExistsException.class, () -> userAdmin.addUser(user2));
    }

    @Test
    void verifyUser() {
        try {
            assertTrue(userAdmin.verifyUser(user1));
            assertFalse(userAdmin.verifyUser(user2));
        } catch (IOException | ClassNotFoundException e) {
            fail(e);
        }
    }

    @Test
    void removeUser() {
        assertDoesNotThrow(() -> userAdmin.removeUser(user1));
        assertDoesNotThrow(() -> userAdmin.removeUser(user2));
        try {
            assertFalse(userAdmin.verifyUser(user1));
        } catch (IOException | ClassNotFoundException e) {
            fail(e);
        }
    }
}