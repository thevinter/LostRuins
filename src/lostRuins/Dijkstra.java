package lostRuins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Dijkstra {
	private static final int UNKNOWN_VALUE = -1; //an unknown value that we actually know eheheheheh
	
	private int rootId;
	
	private ArrayList<DijkstraNode> allNodes;
	private LinkedList<DijkstraNode> unvisitedNodes;
	private double[] dist;
	private int[] prev;
	private int[] numberOfNodesInPath;
	private int[] maxIndexInPath;
	
	
	public Dijkstra(ArrayList<DijkstraNode> allNodes, int rootId) {
		this.allNodes = new ArrayList<>(allNodes);
		unvisitedNodes = new LinkedList<>(allNodes);
		int size = allNodes.size();
		dist = new double[size];
		prev = new int[size];
		numberOfNodesInPath = new int[size];
		maxIndexInPath = new int[size];
		for(int i = 0; i < allNodes.size(); i++) {
			dist[i] = Double.POSITIVE_INFINITY;
			prev[i] = UNKNOWN_VALUE; 
			numberOfNodesInPath[i] = 0;
			maxIndexInPath[i] = 0;
		}
		dist[rootId] = 0;
		this.rootId  = rootId;
	}
	
	public Dijkstra(ArrayList<DijkstraNode> allNodes) {
		this(allNodes, 0);
	}
	
	public void dijkstraAlghoritm() {
		while(unvisitedNodes.size() != 0) {
//			System.out.printf("Completed: %5d/%d nodes - %6.2f%%%n", allNodes.size() - unvisitedNodes.size(), allNodes.size(), (double)(allNodes.size() - unvisitedNodes.size()) / allNodes.size() * 100 );
			DijkstraNode tempNode = minDist();
			int tempId = tempNode.getId();
			unvisitedNodes.remove(findIndex(tempNode));
			for(DijkstraEdge edge : tempNode.getEdges()) {
				int edgeDest = edge.getDestination();
				// if the destination node is not in the unvisited nodes list, then continue
				if(findIndex(edgeDest) == -1){
					continue;
				}
				double alt_dist = dist[tempId] + edge.getWeight();
				// if the new path is worse than the old one, then continue
				if(alt_dist > dist[edgeDest])
					continue;
				// if the distance is the same
				if (alt_dist == dist[edgeDest]) {
					// if the number of nodes of the new path is greater than the NoN of the tempNode + 1, then continue
					if (numberOfNodesInPath[tempId] + 1 > numberOfNodesInPath[edgeDest])
						continue;
					
					// if the two paths are of the same length
					if (numberOfNodesInPath[tempId] + 1 == numberOfNodesInPath[edgeDest]) {
						// if the new node hasn't a greater predecessor than the old one, then continue  
						int newMaxIndex = Math.max(tempId, maxIndexInPath[tempId]);
						if (newMaxIndex < maxIndexInPath[edgeDest])
							continue;
						
						if (newMaxIndex == maxIndexInPath[edgeDest]) {
							int oldOne = prev[edgeDest];
							int newOne = tempId;
							int maxOld = 0;
							int maxNew = 0;
							
							while (oldOne != newOne) {
								if (oldOne > maxOld)
									maxOld = oldOne;
								if (newOne > maxNew)
									maxNew = newOne;
								oldOne = prev[oldOne];
								newOne = prev[newOne];
							}
							
							if (oldOne > newOne)
								continue;
						}
					}
				}

				dist[edgeDest] = alt_dist;
				prev[edgeDest] = tempId;
				numberOfNodesInPath[edgeDest] = numberOfNodesInPath[tempId] + 1;
				maxIndexInPath[edgeDest] = Math.max(maxIndexInPath[tempId], tempId);
			}
		}
	}
	
	public ArrayList<DijkstraNode> getPathTo(int destId) {
		ArrayList<DijkstraNode> out = new ArrayList<>();
		int cost = 0;
		int nextNodeId = destId;
		while (nextNodeId != rootId) {
			DijkstraNode node = allNodes.get(nextNodeId);
			out.add(node);			
			
			nextNodeId = prev[nextNodeId];
		}
		out.add(allNodes.get(nextNodeId));
		Collections.reverse(out);
		return out;
	}
	
	private DijkstraNode minDist() {
		DijkstraNode minNode = null;
		double minDist = Double.POSITIVE_INFINITY;
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

	public double getDistance(int destId) {
		return dist[destId];
	}
}
