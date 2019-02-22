package unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static unit.english.Unit.CELSIUS;
import static unit.english.Unit.FAHRENHEIT;

public class TemperatureUnitTest {

    @Test
    void equalsTemperature() {
        assertEquals(CELSIUS.s(0), FAHRENHEIT.s(32));
        assertEquals(CELSIUS.s(10), FAHRENHEIT.s(50));
        assertEquals(CELSIUS.s(100), FAHRENHEIT.s(212));
        assertEquals(CELSIUS.s(-40), FAHRENHEIT.s(-40));

        assertEquals(FAHRENHEIT.s(32), CELSIUS.s(0));
        assertEquals(FAHRENHEIT.s(50), CELSIUS.s(10));
        assertEquals(FAHRENHEIT.s(212), CELSIUS.s(100));
        assertEquals(FAHRENHEIT.s(-40), CELSIUS.s(-40));

        assertEquals(CELSIUS.s(10).hashCode(), FAHRENHEIT.s(50).hashCode());
    }

    @Test
    void plusTemperature() {
        assertThrows(UnsupportedOperationException.class, () -> CELSIUS.s(10).plus(FAHRENHEIT.s(50)));
    }

    @Test
    void minusTemperature() {
        assertThrows(UnsupportedOperationException.class, () -> FAHRENHEIT.s(32).minus(CELSIUS.s(0)));
    }

}
