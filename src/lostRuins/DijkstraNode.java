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
		out.append("  ID: ");
		out.append(String.format("%5d", id));
		out.append(String.format(" %-17s", "(" + label + ")"));
		out.append("-> vis: ");
		out.append(visited ? "y" : "n");
		return out.toString();
	}
}
