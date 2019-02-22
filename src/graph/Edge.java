package graph;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

// Understands a connection from one Node to another
class Edge {

    private final double cost;
    private final Node target;

    Edge(double cost, Node target) {
        this.cost = cost;
        this.target = target;
    }

    static double totalCosts(List<Edge> edges) {
        return edges.stream()
                .mapToDouble(e -> e.cost)
                .sum();
    }

    static List<Node> nodes(List<Edge> edges) {
        return edges.stream()
                .map(e -> e.target)
                .collect(toList());
    }

    Path path(Node destination, List<Node> visitedNodes, Comparator<Path> strategy) {
        Path path = target.path(destination, visitedNodes, strategy);
        path.add(this);
        return path;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "path=" + cost +
                ", target=" + target +
                '}';
    }
}