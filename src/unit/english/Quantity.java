package unit.english;

import sort.Komparable;

public class Quantity implements Komparable<Quantity> {

    private static final double DELTA = 0.001;

    private final double count;
    private final Unit unit;

    Quantity(final double count, final Unit unit) {
        this.count = count;
        this.unit = unit;
    }

    public Quantity plus(final Quantity other) {
        this.unit.validateCalculation();
        return new Quantity(this.count + convertedAmountFrom(other), this.unit);
    }

    public Quantity minus(final Quantity other) {
        this.unit.validateCalculation();
        return new Quantity(this.count - convertedAmountFrom(other), this.unit);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.equals((Quantity) o);
    }

    private boolean equals(final Quantity other) {
        return this.isCompatible(other) && Math.abs(this.count - convertedAmountFrom(other)) < DELTA;
    }

    private boolean isCompatible(Quantity other) {
        return this.unit.isCompatible(other.unit);
    }

    private double convertedAmountFrom(final Quantity other) {
        return this.unit.convertedAmountFrom(other.unit, other.count);
    }

    @Override
    public int hashCode() {
        return unit.hashCode(count);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "count=" + count +
                ", unit=" + unit +
                '}';
    }

    @Override
    public Quantity better(Quantity other) {
        return this.count > other.convertedAmountFrom(other) ? this : other;
    }
}
