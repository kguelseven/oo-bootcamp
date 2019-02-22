package graph;

final class UnReachablePath implements Path {

    @Override
    public void add(Edge edge) {
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
