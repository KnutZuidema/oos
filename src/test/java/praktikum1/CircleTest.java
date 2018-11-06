package praktikum1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CircleTest {
    private final int expected1 = 0;
    private final int expected2 = 10;
    private Circle circle1, circle2, circle3;

    @BeforeEach
    void setUp() {
        circle1 = new Circle();
        circle2 = new Circle(expected2, expected2, expected2);
        circle3 = new Circle(circle2);
    }

    @Test
    void getRadius() {
        assertEquals(expected1, circle1.getRadius());
        assertEquals(expected2, circle2.getRadius());
        assertEquals(expected2, circle3.getRadius());
    }

    @Test
    void setRadius() {
        circle1.setRadius(expected2);
        assertEquals(expected2, circle1.getRadius());
    }

    @Test
    void equals() {
        assertEquals(circle2, circle3);
        assertNotEquals(circle1, circle2);
    }

    @Test
    void toString1() {
        assertEquals(String.format("praktikum1.Circle(%1$d, %1$d, radius=%1$d)", expected1), circle1.toString());
    }
}