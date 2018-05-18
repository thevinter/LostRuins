package lostRuins;

import java.util.ArrayList;

public class DijkstraNode {
	private String label;
	private int id;
	private boolean visited;
	private ArrayList<DijkstraEdge> edges = new ArrayList<>();
	
	public DijkstraNode(int id, String label) {
		this.id = id;
		this.label = label;
		visited = false;
	}
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited() {
		this.visited = true;
	}

	public int getId() {
		return this.id;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public ArrayList<DijkstraEdge> getEdges(){
		return edges;
	}
	
	public DijkstraEdge getEdgeTo(int destinationId) {
		for (DijkstraEdge edge : edges) {
			if (edge.getDestination() == destinationId)
				return edge;
		}
		return null;
	}

	public void addEdge(DijkstraEdge newEdge) {
		edges.add(newEdge);
	}
	
	public String toString() {
		StringBuffer out = new StringBuffer();
		out.append("\tID: ");
		out.append(id);
		out.append(", name: ");
		out.append(label);
		return out.toString();
	}
}
