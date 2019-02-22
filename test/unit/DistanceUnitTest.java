package unit;

import org.junit.jupiter.api.Test;
import unit.english.Quantity;
import unit.english.Unit;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static unit.english.Unit.CELSIUS;
import static unit.english.Unit.CHAIN;
import static unit.english.Unit.FAHRENHEIT;
import static unit.english.Unit.FOOT;
import static unit.english.Unit.FURLONG;
import static unit.english.Unit.INCH;
import static unit.english.Unit.MILE;
import static unit.english.Unit.OUNCE;
import static unit.english.Unit.TABLESPOON;
import static unit.english.Unit.TEASPOON;
import static unit.english.Unit.YARD;

public class DistanceUnitTest {

    @Test
    void plusDistance() {
        assertEquals(FOOT.s(4), YARD.s(1).plus(INCH.s(12)));
        assertEquals(FURLONG.s(0.2), CHAIN.s(1).plus(CHAIN.s(1)));
    }

    @Test
    void equalsDistance() {
        assertEquals(YARD.s(3), FOOT.s(9));
        assertEquals(CHAIN.s(2), YARD.s(44));
        assertEquals(YARD.s(1), INCH.s(36));
    }

    @Test
    void equalsDifferentUnits() {
        assertNotEquals(INCH.s(1), TEASPOON.s(1));
    }

    @Test
    void plusDifferentUnits() {
        assertThrows(UnsupportedOperationException.class, () -> YARD.s(1).plus(OUNCE.s(1)));
        assertThrows(UnsupportedOperationException.class, () -> CELSIUS.s(1).plus(OUNCE.s(1)));
    }

    @Test
    void minusDistance() {
        assertEquals(FURLONG.s(6), MILE.s(1).minus(CHAIN.s(20)));
    }

    @Test
    void minusDifferentUnits() {
        assertThrows(UnsupportedOperationException.class, () -> YARD.s(1).minus(OUNCE.s(1)));
    }

    @Test
    void hashCodeTest() {
        Set<Quantity> set = new HashSet<>();
        set.add(YARD.s(3));
        set.add(INCH.s(108));
        set.add(FOOT.s(9));
        assertEquals(1, set.size());
    }

    @Test
    void plusMinusDistance() {
        assertEquals(FURLONG.s(8), MILE.s(1).minus(CHAIN.s(20)).plus(CHAIN.s(20)));
    }

}
