package lostRuins;

import java.util.ArrayList;

/**
 * <p>This class represents a single node in a graph stored in the {@code Dijkstra} class.</p>
 * <p>Every instance of this class contains a label, a unique id, a visited flag (useful during Dijkstra's algorithm execution) and a list of the edges starting from this node.
 */
public class DijkstraNode {
	private String label;
	private int id;
	private boolean visited;
	private ArrayList<DijkstraEdge> edges = new ArrayList<>();

	/**
	 * <p>Constructor for a new node object, providing the id of the node and a label;
	 * @param id 
	 * @param label
	 */
	public DijkstraNode(int id, String label) {
		this.id = id;
		this.label = label;
		visited = false;
	}
	
	/**
	 * <p>Getter for the visited flag (used in the algorithm)
	 * @return True if the node has been visited by the algorithm, false otherwise
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * <p>Sets the status of the visited flag to true (once the algorithm reaches this node)
	 */
	public void setVisited() {
		this.visited = true;
	}

	/**
	 * <p>Getter for the identifying id of the node
	 * @return The id of the node
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * <p>Getter for the label (the name) of the node
	 * @return The label of the node
	 */
	public String getLabel() {
		return this.label;
	}
	
	/**
	 * <p>Getter for the list of the edges
	 * @return An {@code ArrayList<DijkstraEdge>} containing all of the edges starting from this node
	 */
	public ArrayList<DijkstraEdge> getEdges(){
		return new ArrayList<>(edges);
	}
	
	/**
	 * <p>Adds a new edge to this node
	 * @param newEdge A {@code DijkstraEdge}
	 */
	public void addEdge(DijkstraEdge newEdge) {
		edges.add(newEdge);
	}
}
