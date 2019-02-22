package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class ReachablePath extends Path {

    static final Comparator<Path> LEAST_COST = Comparator.comparingDouble(Path::cost);
    static final Comparator<Path> FEWEST_HOPS = Comparator.comparingInt(Path::hopsCount);

    private final List<Edge> edges = new ArrayList<>();

    ReachablePath() {
    }

    @Override
    void add(Edge edge) {
        edges.add(edge);
    }

    @Override
    public int hopsCount() {
        return Edge.nodes(edges).size();
    }

    @Override
    public double cost() {
        return Edge.totalCosts(edges);
    }

    @Override
    public String toString() {
        return "Path{" +
                "edges=" + edges +
                '}';
    }
}
