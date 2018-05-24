package lostRuins;
/**
 * <p>This class represents a edge between two nodes in a graph used in the execution of Dijkstra's algorithm 
 */
public class DijkstraEdge {
	double weight;
	int fromId;
	int toId;
	
	/**
	 * <p>Constructor of an edge
	 * @param sourceId The id of the starting node of the link
	 * @param destinationId The id of the destination node of the link
	 * @param weight The weight of the edge
	 */
	public DijkstraEdge(int sourceId, int destinationId, double weight) {
		this.fromId = sourceId;
		this.toId = destinationId;
		this.weight = weight;
	}
	
	/**
	 * <p>Getter for the destination id of the link
	 * @return The id of the destination of the link
	 */
	public int getDestination() {
		return toId;
	}
	
	/**
	 * <p>Getter for the weight of the link
	 * @return The weight of the link
	 */
	public double getWeight() {
		return weight;
	}
}
