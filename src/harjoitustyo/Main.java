import java.util.*;
import model.*;

public class Main {

    public static void main(String[] args) {

        Graph graph;
        DijkstraAlgorithm dijkstra;
        Mapper mapper = new Mapper();
        Scanner scanner = new Scanner(System.in);

        String sourceFilePath = "C:\\temp\\KAUPUNKI.csv";   // STATIC PATH TO THE SOURCE FILE
        System.out.println( "Starting...\nSource file location: " + sourceFilePath );

        /* CSV READER TEST PRINT
        CsvReader csvReader = new CsvReader();
        csvReader.printSourceTest(sourceFilePath);
        */

        /* ALGORITHM & PRINT TEST WITHOUT MAPPING
        List<Vertex> vertexes = new ArrayList<Vertex>(){};
        vertexes.add(new Vertex(1, "yks"));
        vertexes.add(new Vertex(2, "kaks"));
        vertexes.add(new Vertex(3, "kol"));
        List<Edge> edges = new ArrayList<Edge>(){};
        edges.add(new Edge(1,vertexes.get(0), vertexes.get(1), 2));
        edges.add(new Edge(1,vertexes.get(1), vertexes.get(2), 3));
        graph = new Graph(vertexes,edges);
        */

        graph = mapper.mapGraph(sourceFilePath);  // map source data into graph of vertexes and edges

        System.out.print("\nMapped cities:");       // print all cities found from csv-files
        for (int i = 0; i < graph.getVertexes().size(); i++) {
            System.out.print(String.format("\n%1$d %2$s",
                    graph.getVertexes().get(i).getId(),
                    graph.getVertexes().get(i).getName() ));
        }

        System.out.print("\nEnter number of starting city: ");
        int startInput = scanner.nextInt();
        startInput = startInput - 1;     // minus one because computers

        System.out.print("Enter number of destination city: ");
        int endInput = scanner.nextInt();
        endInput = endInput - 1;     // minus one because computers

        System.out.println(String.format("Start city %1$s, destination city %2$s",
                graph.getVertexes().get(startInput).getName(),
                graph.getVertexes().get(endInput).getName() ));

        dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(graph.getVertexes().get(startInput));   // execute algorithm for mininum distances
        LinkedList<Vertex> path = dijkstra.getPath(graph.getVertexes().get(endInput));  // get vertexes prior end/target
        Integer distance = dijkstra.getDistanceToTarget(graph.getVertexes().get(endInput));     // get distance to target

        for (int i = 0; i < path.size(); i++) {
            if(i > 0)
                System.out.print(" - ");
            System.out.print(path.get(i).getName());
        }
        System.out.println(String.format("\nKokonaismatka: %1$s", distance));

    }
}
