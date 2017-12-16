import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvReader {

    public List<String> csvReader(String csvFilePath) {
        BufferedReader br = null;
        String line = "";
        List<String> sourceLines = new ArrayList<String>();   // array-list of all rows, each entry contains

        try {
            br = new BufferedReader(new FileReader(csvFilePath));
            br.readLine();  // csv-header line
            while ((line = br.readLine()) != null) {    // read rows and create new Vertex for each Kaupunki
                sourceLines.add(line);  // read every lien into list
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sourceLines;     // return read lines
    }

    // TEST PRINT
    public void printSourceTest(String csvFilePath) {
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(csvFilePath));
            br.readLine(); // skip header line
            while ((line = br.readLine()) != null) {
                String[] sourceRow = line.split(",");
                System.out.print( "\n" + sourceRow[0] + ", naapurit: " );
                for(int i = 1; i < sourceRow.length; i++) {
                    System.out.print( "\t\t" + sourceRow[i] + "\t\t" );
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
