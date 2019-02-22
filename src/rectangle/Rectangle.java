package rectangle;

import sort.Komparable;

import java.util.Objects;

// Understands a four-sided polygon with sides at right angles
public class Rectangle implements Komparable<Rectangle> {

    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        if (width <= 0.0 || height <= 0.0) throw new IllegalArgumentException("Dimensions must be > 0");
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.width, width) == 0 &&
                Double.compare(rectangle.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public Rectangle better(Rectangle other) {
        return (this.area() > (other.area())) ? this : other;
    }
}
