import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Class for testing the breadth first search
 */
public class TestBFS {

    private static String testnumber = "14";

    /**
     * Jespers method to read a txt file and save it as an ArrayList
     *
     * @return ArrayList of words
     * @throws IOException
     */
    public static ArrayList<String> readFile() throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("files/words-" + testnumber + "-data.txt")));

        ArrayList<String> words = new ArrayList<>();

        while (true) {
            String word = r.readLine();
            if (word == null) {
                break;
            }
            assert word.length() == 5;  // indatakoll, om man kör med assertions på
            words.add(word);
        }

        return words;
    }

    /**
     * Loads a txt testfile and saves it in a String
     * Runs BFS on the ArrayList and prints the distance between words in the testfile string
     *
     * @param bfs BFS object
     * @throws IOException
     */
    public static void testCase(BFS bfs) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("files/words-" + testnumber + "-test.txt")));

        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            assert line.length() == 11; // indatakoll, om man kör med assertions på
            String start = line.substring(0, 5);
            String goal = line.substring(6, 11);

            System.out.println(bfs.getDistance(start, goal));

            bfs.clearQueue();
            bfs.clearDistances();
        }
    }

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            ArrayList<String> words = readFile(); //Reads the textfile and saves it as an ArrayList

            BFS bfs = new BFS();

            for (String word : words) { // adds a vertex object for each word in the textfile to the vertexHashMap HashMap
                bfs.vertexHashMap.put(word,new Vertex(word));
            }

            bfs.connectVertices(words); //Checks if words are linked and saves linked words as childnodes in a hashmap in each node

            testCase(bfs); //Reads testfile and prints distance between words in the file

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
