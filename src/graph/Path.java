package graph;

public abstract class Path {

    static Path UNREACHABLE_PATH = new UnReachablePath();

    abstract void add(Edge edge);

    public abstract int hopsCount();

    public abstract double cost();

}
