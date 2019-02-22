/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package unit;

import org.junit.jupiter.api.Test;
import rectangle.Rectangle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Ensures Rectangle operates correctly
class RectangleTest {

    @Test
    void area() {
        assertEquals(24.0, new Rectangle(4.0, 6.0).area());
    }

    @Test
    void perimeter() {
        assertEquals(20.0, new Rectangle(4.0, 6.0).perimeter());
    }

    @Test
    void validDimensions() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 6));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(4, 0));
    }
}