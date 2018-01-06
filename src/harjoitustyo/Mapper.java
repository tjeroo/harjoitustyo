import java.util.ArrayList;
import java.util.List;
import model.*;

public class Mapper {

    private CsvReader csvReader = new CsvReader();

    public Graph mapGraph(String csvFilePath) {
        List<Vertex> vertexes;
        List<Edge> edges;

        vertexes = mapVertexes(csvFilePath);
        edges = mapEdges(csvFilePath, vertexes);

        Graph ret = new Graph(vertexes, edges);
        return ret;
    }

    public List<Vertex> mapVertexes(String csvFilePath) {
        List<String> lines;
        List<Vertex> ret = new ArrayList<Vertex>();

        lines = csvReader.csvReader(csvFilePath);

        for ( int i = 0; i < lines.size(); i++ ) {    // read rows and create new Vertex for each Kaupunki

            String[] sourceRow = lines.get(i).split(",");   // read line and split data by ',' to array

            Vertex newKaupunki = new Vertex(i+1, sourceRow[0]);    // create vertex for Kaupunki
            ret.add(newKaupunki);  // add Kaupunki to list of vertexes
        }
        return ret;
    }

    public List<Edge> mapEdges(String csvFilePath, List<Vertex> vertexes) {
        List<String> lines;
        List<Edge> ret = new ArrayList<Edge>();
        Vertex source;
        Vertex destination;

        lines = csvReader.csvReader(csvFilePath);
        for ( int i = 0; i < lines.size(); i++ ) {
            String[] sourceRow = lines.get(i).split(",");   // read line and split data by ',' to array
            for ( int j = 0; j < vertexes.size(); j++ ) {
                if (sourceRow[0].equals(vertexes.get(j).getName())) {
                    source = vertexes.get(j);
                    for (int k = 1; k < sourceRow.length; k++) {
                        if (sourceRow[k].length() > 0) {
                            String[] splitted = sourceRow[k].split(" ");    // split naapuri and etaisyys
                            for (int l = 0; l < vertexes.size(); l++) {
                                if (splitted[0].equals(vertexes.get(l).getName())) {
                                    destination = vertexes.get(l);
                                    Edge newValimatka = new Edge(l + 1, source, destination, Integer.parseInt(splitted[1]));
                                    ret.add(newValimatka);
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

}
