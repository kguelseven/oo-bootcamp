/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Understands its neighbors
public class Node {

    private final String name;
    private final List<Edge> edges = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }

    public boolean canReach(Node destination) {
        return path(destination, ReachablePath.FEWEST_HOPS) != Path.UNREACHABLE_PATH;
    }

    public int hopCount(Node destination) {
        return requireReachablePath(path(destination, ReachablePath.FEWEST_HOPS)).hopsCount();
    }

    public double cost(Node destination) {
        return path(destination).cost();
    }

    public Path path(Node destination) {
        return requireReachablePath(path(destination, ReachablePath.LEAST_COST));
    }

    private Path path(Node destination, Comparator<Path> comparator) {
        return path(destination, new ArrayList<>(), comparator);
    }

    private Path requireReachablePath(Path path) {
        if(path == Path.UNREACHABLE_PATH) {
            throw new IllegalArgumentException();
        }
        return path;
    }

    Path path(Node destination, List<Node> visitedNodes, Comparator<Path> strategy) {
        if (this == destination) return new ReachablePath();
        if (visitedNodes.contains(this)) return Path.UNREACHABLE_PATH;
        return edges.stream()
                .map(e -> e.path(destination, copyWithNode(visitedNodes), strategy))
                .min(strategy)
                .orElse(Path.UNREACHABLE_PATH);
    }

    private List<Node> copyWithNode(List<Node> visitedNodes) {
        ArrayList<Node> nodes = new ArrayList<>(visitedNodes);
        nodes.add(this);
        return nodes;
    }

    public EdgeBuilder cost(double amount) {
        return new EdgeBuilder(amount, edges);
    }

    public static class EdgeBuilder {
        private final double cost;
        private final List<Edge> edges;

        private EdgeBuilder(double cost, List<Edge> edges) {
            this.cost = cost;
            this.edges = edges;
        }

        public Node to(Node target) {
            edges.add(new Edge(cost, target));
            return target;
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }
}