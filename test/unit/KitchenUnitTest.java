package unit;

import org.junit.jupiter.api.Test;
import unit.english.Quantity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static unit.english.Unit.INCH;
import static unit.english.Unit.OUNCE;
import static unit.english.Unit.TABLESPOON;
import static unit.english.Unit.TEASPOON;

public class KitchenUnitTest {

    @Test
    void hashCodeDifferentUnitsTest() {
        Set<Quantity> set = new HashSet<>();
        set.add(INCH.s(1));
        set.add(TEASPOON.s(1));
        assertEquals(2, set.size());
    }

    @Test
    void equalsKitchen() {
        assertEquals(TEASPOON.s((double) 3), TABLESPOON.s((double) 1));
        assertEquals(TABLESPOON.s((double) 1), TEASPOON.s((double) 3));
        assertEquals(TABLESPOON.s((double) 2), OUNCE.s((double) 1));
        assertEquals(TABLESPOON.s((double) 1), OUNCE.s(0.5));
    }

    @Test
    void plusKitchen() {
        assertEquals(TEASPOON.s((double) 2), TEASPOON.s((double) 1).plus(TEASPOON.s((double) 1)));
        assertEquals(OUNCE.s(1.333), OUNCE.s((double) 1).plus(TEASPOON.s((double) 2)));
    }

    @Test
    void minusKitchen() {
        assertEquals(TEASPOON.s((double) 0), TEASPOON.s((double) 1).minus(TEASPOON.s((double) 1)));
        assertEquals(OUNCE.s(0.666), OUNCE.s((double) 1).minus(TEASPOON.s((double) 2)));
        assertEquals(TEASPOON.s((double) -15), OUNCE.s((double) 1).minus(TABLESPOON.s((double) 7)));
        assertEquals(OUNCE.s(-2.5), OUNCE.s((double) 1).minus(TABLESPOON.s((double) 7)));
    }

    @Test
    void hashCodeKitchenTest() {
        Set<Quantity> set = new HashSet<>();
        set.add(OUNCE.s((double) 1));
        set.add(OUNCE.s((double) 1));
        set.add(TABLESPOON.s((double) 2));
        assertEquals(1, set.size());
    }

    @Test
    void plusMinusKitchen() {
        assertEquals(OUNCE.s(0.833), OUNCE.s((double) 1).plus(TEASPOON.s((double) 2).minus(TABLESPOON.s((double) 1))));
    }

}
