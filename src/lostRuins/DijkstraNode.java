package lostRuins;

import java.util.ArrayList;

public class DijkstraNode {
	private String label;
	private int id;
	private ArrayList<DijkstraEdge> edges = new ArrayList<>();
	
	public int getId() {
		return this.id;
	}
	
	public ArrayList<DijkstraEdge> getEdges(){
		return edges;
	}
}
