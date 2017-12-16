import java.io.Console;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Graph graph;
        DijkstraAlgorithm dijkstra;
        Mapper mapper = new Mapper();
        Scanner reader = new Scanner(System.in);

        String sourceFilePath = "C:\\temp\\KAUPUNKI.csv";
        System.out.println( "Starting...\nSource file location: " + sourceFilePath );

        /* CSV READER TEST PRINT
        CsvReader csvReader = new CsvReader();
        csvReader.printSourceTest(sourceFilePath);
         */

        graph = mapper.mapGraph(sourceFilePath);  // map source data into graph of vertexes and edges

        System.out.print("\nMapped cities:");       // print all cities found from csv-files
        for (int i = 0; i < graph.getVertexes().size(); i++) {
            System.out.print(String.format("\n%1$d %2$s",
                    graph.getVertexes().get(i).getId(),
                    graph.getVertexes().get(i).getName() ));
        }

        System.out.print("\nEnter number of starting city: ");
        int startInput = reader.nextInt();
        startInput = startInput - 1;     // minus one because computers

        System.out.print("Enter number of destination city: ");
        int endInput = reader.nextInt();
        endInput = endInput - 1;     // minus one because computers

        System.out.println(String.format("Start city %1$s, destination city %2$s",
                graph.getVertexes().get(startInput).getName(),
                graph.getVertexes().get(endInput).getName() ));

        dijkstra = new DijkstraAlgorithm(graph);        // add graph into dijkstra
        dijkstra.execute(graph.getVertexes().get(startInput));   // execute algorithm based on graph
        LinkedList<Vertex> path = dijkstra.getPath(graph.getVertexes().get(endInput));  // target route

        //System.out.println(String.format("Minimun destination given: %1$s", path.get(0).getName() ));
        for (Vertex vertex : path) {
            System.out.println(vertex);
        }   //TODO: kirjaa lähtöpisteen ja loppupisteen, vois muokata siten että printtaa kaikki välivaiheet
        // TODO: ei laske etäisyyttä, se täytyy weightin mukana lisätä tonne

    }
}
