package graph;

final class UnReachablePath extends Path {

    @Override
    void add(Edge edge) {
        // noop
    }

    @Override
    public int hopsCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public double cost() {
        return Double.POSITIVE_INFINITY;
    }
}
