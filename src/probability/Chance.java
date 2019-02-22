package probability;

import sort.Komparable;

// Understands the likelihood of something occurring
public class Chance implements Komparable<Chance> {

    private static final double CERTAIN_FRACTION = 1.0;
    private static final double DELTA = 0.000000001;
    private final double fraction;

    public Chance(double likelihoodAsFraction) {
        if (likelihoodAsFraction < 0.0 || likelihoodAsFraction > CERTAIN_FRACTION)
            throw new IllegalArgumentException("Fraction must be between 0.0 and 1.0, inclusive");
        fraction = likelihoodAsFraction;
    }

    @Override
    public boolean equals(Object other) {
        return this == other ||
                other != null && other.getClass() == Chance.class && this.equals((Chance) other);
    }

    private boolean equals(Chance other) {
        return Math.abs(this.fraction - other.fraction) < DELTA;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(fraction);
    }

    public Chance not() {
        return new Chance(CERTAIN_FRACTION - fraction);
    }

    public Chance and(Chance other) {
        return new Chance(this.fraction * other.fraction);
    }

    // Implemented with DeMorgan's Law https://en.wikipedia.org/wiki/De_Morgan%27s_laws
    public Chance or(Chance other) {
        return this.not().and(other.not()).not();
    }

    @Override
    public Chance better(Chance other) {
        return (this.fraction > other.fraction) ? this : other;
    }
}