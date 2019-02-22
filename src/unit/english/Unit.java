package unit.english;

import java.util.Objects;

public class Unit {

    private final static Object DISTANCE = new Object();
    public final static Unit INCH = new Unit(DISTANCE, false);
    public final static Unit FOOT = new Unit(12, INCH);
    public final static Unit YARD = new Unit(3, FOOT);
    public final static Unit CHAIN = new Unit(22, YARD);
    public final static Unit FURLONG = new Unit(10, CHAIN);
    public final static Unit MILE = new Unit(8, FURLONG);

    private final static Object KITCHEN = new Object();
    public final static Unit TEASPOON = new Unit(KITCHEN, false);
    public final static Unit TABLESPOON = new Unit(3, TEASPOON);
    public final static Unit OUNCE = new Unit(2, TABLESPOON);

    private final static Object TEMPERATURE = new Object();
    public final static Unit CELSIUS = new Unit(TEMPERATURE, true);
    public final static Unit FAHRENHEIT = new Unit(5 / 9.0, 32, CELSIUS);

    private final Object type;
    private final double baseUnitRatio;
    private final double offset;
    private final boolean isInterval;

    private Unit(final Object type, final boolean isInterval) {
        this.type = type;
        this.isInterval = isInterval;
        baseUnitRatio = 1.0;
        offset = 0.0;
    }

    private Unit(final double relativeRatio, final double offset, final Unit relativeUnit) {
        this.type = relativeUnit.type;
        this.isInterval = relativeUnit.isInterval;
        baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;
        this.offset = offset;
    }

    private Unit(final double relativeRatio, final Unit relativeUnit) {
        this(relativeRatio, 0.0, relativeUnit);
    }

    double convertedAmountFrom(final Unit other, final double otherCount) {
        if (!this.isCompatible(other))
            throw new UnsupportedOperationException("Not able to transform to other type of unit");
        return (otherCount - other.offset) * other.baseUnitRatio / this.baseUnitRatio + this.offset;
    }

    int hashCode(final double count) {
        return Double.hashCode((count - offset) * baseUnitRatio);
    }

    public Quantity s(final double count) {
        return new Quantity(count, this);
    }

    public void validateCalculation() {
        if (isInterval) throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "Unit{" +
                "baseUnitRatio=" + baseUnitRatio +
                "type=" + type +
                '}';
    }

    boolean isCompatible(Unit other) {
        return Objects.equals(this.type, other.type);
    }
}
