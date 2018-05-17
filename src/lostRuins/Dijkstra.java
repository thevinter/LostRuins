package lostRuins;

import java.util.ArrayList;
import java.util.LinkedList;

public class Dijkstra {
	private static final int UNKNOWN_VALUE = -1; //an unknown value that we actually know eheheheheh
	
	private LinkedList<DijkstraNode> unvisitedNodes;
	private int[] dist;
	private int[] prev;
	
	
	public Dijkstra(ArrayList<DijkstraNode> allNodes, int rootId) {
		unvisitedNodes = new LinkedList<>(allNodes);
		dist = new int[allNodes.size()];
		prev = new int[allNodes.size()];
		for(int i = 0; i < allNodes.size(); i++) {
			dist[i] = Integer.MAX_VALUE;
			prev[i] = UNKNOWN_VALUE; 
		}
		dist[rootId] = 0;
	}
	
	public Dijkstra(ArrayList<DijkstraNode> allNodes) {
		this(allNodes, 0);
	}
	
	public void dijkstraAlghoritm() {
		while(unvisitedNodes.size() != 0) {
			DijkstraNode tempNode = minDist();
			unvisitedNodes.remove(findIndex(tempNode));
			for(DijkstraEdge edge : tempNode.getEdges()) {
				int edgeDest = edge.getDestination();
				if(findIndex(edgeDest) == -1){
					continue;
				}
				int alt_dist = dist[tempNode.getId()] + edge.getWeight();
				if(alt_dist < dist[edgeDest]) {
					dist[edgeDest] = alt_dist;
					prev[edgeDest] = tempNode.getId();
				}
			}
		}
	}
	
	private DijkstraNode minDist() {
		DijkstraNode minNode = null;
		int minDist = Integer.MAX_VALUE;
		for (DijkstraNode node : unvisitedNodes) {
			if(dist[node.getId()] < minDist) {
				minDist = dist[node.getId()];
				minNode = node;
			}
		}
		return minNode;
	}
	
	private int findIndex(DijkstraNode node) {
		return findIndex(node.getId());
	}
	
	
	private int findIndex(int id) {
		int index = -1;
		for (int i = 0; i < unvisitedNodes.size(); i++) {
			if(unvisitedNodes.get(i).getId() == id) {
				index = i;
				break;
			}
		}
		return index;
	}
}
