public class Edge {

    private final Integer id;
    private final Vertex source;
    private final Vertex destination;
    private final int weight;

    public Edge(Integer id, Vertex source, Vertex destination, int weight) {
        this.id = id;
        this.source = source;//new Vertex(source.getId(), source.getName());
        this.destination = destination;//new Vertex(destination.getId(), destination.getName());
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }
    public Vertex getDestination() {
        return destination;
    }
    public Vertex getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}
