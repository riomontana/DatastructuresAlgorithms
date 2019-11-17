import java.util.HashSet;

/**
 * Class describing a vertex
 */
public class Vertex {
    public HashSet<Vertex> childVertices;
    public String word;
    public boolean queued;
    public int distance;

    /**
     * Constructor
     * @param word String word from text file
     */
    public Vertex(String word) {
        this.childVertices = new HashSet<>();
        this.distance = 0;
        this.word = word;
    }
}
