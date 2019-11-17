import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Class used to do Breadth First Search in a directed graph
 */
public class BFS {
    private LinkedList<Vertex> vertexList;
    public HashMap<String, Vertex> vertexHashMap;

    /**
     * Constructor
     */
    public BFS() {
        vertexList = new LinkedList<>();
        vertexHashMap = new HashMap<>();
    }

    /**
     * To find distance between the words in test-file
     * @param firstWord first word in test file line
     * @param lastWord second word in test file line
     * @return int Distance between the words in test file
     */
    public int getDistance(String firstWord, String lastWord) {
        vertexList.add(vertexHashMap.get(firstWord)); //Adds the vertex to the LinkedList

        while(!vertexList.isEmpty()) { //While a vertex is in the LinkedList
            Vertex vertex = vertexList.remove(); //Retreive the first vertex in the vertexList
            
            if(vertex.word.equals(lastWord)) { //If the vertex is the 2 word in test file line
                vertexList.clear(); //Removes all vertexList from LinkedList
                return vertex.distance; //returns the distance between the first word vertex and last word vertex (0 if same word)
            }

            for(Vertex childVertex : vertex.childVertices) { //Iterates all child vertexList for selected vertex
                if(!childVertex.queued) { //checks if vertex is added to the LinkedList
                    childVertex.distance = vertex.distance + 1; //sets the childs distance to the selected vertexList distance +1
                    vertexList.add(childVertex); //Adds the child to LinkedList
                    childVertex.queued = true; //Marks the child vertex as added to vertexList
                }
            }
        }

        vertexList.clear(); //Removes all vertexHashMap from LinkedList
        return -1; //Returns -1 if first word don't have connection to last word in test-file line
    }

    /**
     * Adds a child node to a node in HashMap if words are connected
     * @param words ArrayList of all words from inputfile
     */
    public void connectVertices(ArrayList<String> words) {

        for(int i = 0; i < words.size(); i++) { // Iterate all words in input file (word-1)
            for(int j = 0; j < words.size(); j++) { // Iterate all words in input file (word-2)

                String firstWord = words.get(i);
                String secondWord = words.get(j);

                if(i != j) { //Checks if word-1 is not the same as word-2
                    if(isConnected(firstWord,secondWord)) { //Checks if word-1 and word-2 is connected
                        vertexHashMap.get(firstWord).childVertices.add(vertexHashMap.get(secondWord)); //Adds word-2 as childNode to word-1
                    }
                }
            }
        }
    }

    /**
     * Check if two words are connected
     * @param word1 first word
     * @param word2 second word
     * @return true or false
     */
    public boolean isConnected(String word1, String word2) {
        StringBuilder secondWord = new StringBuilder(word2); //Creates a stringBuilder object with word2

        for(int i = 1; i < word1.length(); i++) { // iterates word1
            //Checks if the letter is present in secondWord and returns the index of letter or -1 if not present
            int charIndex = secondWord.indexOf(word1.substring(i,i+1));

            if(charIndex != -1) { // if charIndex is not -1
                secondWord.deleteCharAt(charIndex); // remove char at index in secondWord
            }
        }

        if (secondWord.length() == 1){
            return true; //Returns true if only 1 char remains (4 chars have been removed)
        } else{
            return false; //Returns false if more than 1 char remains
        }
    }

    /**
     * Sets distance to zero on all vertices in vertexHashMap
     */
    public void clearDistances() {
        for (String key : vertexHashMap.keySet()){
            vertexHashMap.get(key).distance = 0;
        }
    }

    /**
     * Sets queued to false on all vertices in vertexHashMap
     */
    public void clearQueue() {
        for (String key : vertexHashMap.keySet()){
            vertexHashMap.get(key).queued = false;
        }
    }
}
