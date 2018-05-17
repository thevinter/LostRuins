package lostRuins;

public class DijkstraEdge {
	double weight;
	int fromId;
	int toId;
	
	public DijkstraEdge(int sourceId, int destinationId, double weight) {
		this.fromId = sourceId;
		this.toId = destinationId;
		this.weight = weight;
	}
	
	public int getDestination() {
		return toId;
	}
	
	public double getWeight() {
		return weight;
	}
}
