package userManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private final String id1 = "id1";
    private final char[] password1 = {'p', 'w', '1'};
    private User user1, user2;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user2 = new User(id1, password1);
    }

    @Test
    void getId() {
        assertEquals("", user1.getId());
        assertEquals(id1, user2.getId());
    }

    @Test
    void getPassword() {
        assertTrue(Arrays.equals(new char[0], user1.getPassword()));
        assertTrue(Arrays.equals(password1, user2.getPassword()));
    }

    @Test
    void toString1() {
        assertEquals("User(id=, password=)", user1.toString());
        assertEquals("User(id=id1, password=pw1)", user2.toString());
    }

    @Test
    void equals() {
        assertNotEquals(user1, new Object());
        assertNotEquals(user1, null);
        assertNotEquals(user1, user2);
        user1 = new User(id1, password1);
        assertEquals(user1, user2);
    }
}