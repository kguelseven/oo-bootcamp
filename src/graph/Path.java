package graph;

public interface Path {

    static Path UNREACHABLE_PATH = new UnReachablePath();

    void add(Edge edge);

    int hopsCount();

    double cost();

}
