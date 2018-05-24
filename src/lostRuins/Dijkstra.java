package lostRuins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * <p>This class is used to store a weighted directed graph and to compute the shortest path between nodes using Dijkstra's algorithm</p>
 * <p>The format of the data must follow those rules:<p>
 * <ul><li>Every node is represented by a {@link DijkstraNode} object, containing all of the data and the edges</li>
 * <li>Every node will contain a {@code label}, a unique {@code id} and a list of {@code DijkstraEdge} object containing the data for all of the edges starting from such node</li></ul>
 */
public class Dijkstra {
	private static final int UNKNOWN_VALUE = -1; //an unknown value that we actually know eheheheheh
	
	private int rootId;
	
	private ArrayList<DijkstraNode> allNodes;
	private LinkedList<DijkstraNode> unvisitedNodes;
	private double[] dist;
	private int[] prev;
	private int[] numberOfNodesInPath;
	private int[] maxIndexInPath;
	
	/**
	 * <p>Constructor to create a new instance, providing an {@code ArrayList<DijkstraNode>} containing the nodes and the id of the root node (the one the algorithm will start from)</p>
	 * <p>The parameter {@link #allNodes} must have this structure:
	 * <ul><li>The n-th element in the {@code ArrayList} MUST have a unique {@code id} of n</li>
	 * <li>No node will therefore have an {@code id} greater than or equal to the of size of the {@code ArrayList}</li></ul>
	 * @param allNodes An {@code ArrayList<DijkstraNode>} containing the whole graph (see above for the structure)
	 * @param rootId The id of the starting node for the algorithm
	 */
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
	
	/**
	 * <p>Constructor to create a new instance, providing an {@code ArrayList<DijkstraNode>} containing the nodes<br>
	 * The first element of the list will be considered the starting node for the algorithm</p>
	 * <p>The parameter {@link #allNodes} must have this structure:
	 * <ul><li>The n-th element in the {@code ArrayList} MUST have a unique {@code id} of n</li>
	 * <li>No node will therefore have an {@code id} greater than or equal to the of size of the {@code ArrayList}</li></ul>
	 * @param allNodes An {@code ArrayList<DijkstraNode>} containing the whole graph (see above for the structure)
	 */
	public Dijkstra(ArrayList<DijkstraNode> allNodes) {
		this(allNodes, 0);
	}
	
	/**
	 * <p>This method acutally computes Dijkstra's algorithm on the stored graph, starting from the root node specified in the constructor.</p>
	 * <p>This implementation will also have some more controls over which is the best path, other then purely the length.<br>
	 * In fact, if two paths have exactly the same length, the best path will be:
	 * <ul><li>The one with the least number of nodes</li>
	 * <li>If still tied, the one passing through the node with the largest {@code id} value</li>
	 */
	public void dijkstraAlghoritm() {
		while(!unvisitedNodes.isEmpty()) {
			DijkstraNode tempNode = minDist();
			int tempId = tempNode.getId();
			removeVisited(tempNode, unvisitedNodes);
			tempNode.setVisited();
			for(DijkstraEdge edge : tempNode.getEdges()) {
				int edgeDest = edge.getDestination();
				// if the destination node is not in the unvisited nodes list, then continue
				if(allNodes.get(edge.getDestination()).isVisited()){
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
							
							if (maxOld > maxNew)
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
	
	/**
	 * <p>Private method used to remove a visited node from a given list of unvisited nodes.</p>
	 * <p>The identifying value to compare two nodes will be the {@code id}, as it must be unique.
	 * @param node The node to be removed from the list
	 * @param nodes A {@code LinkedList<DijkstraNode>} representing the list of unvisited nodes
	 */
	private void removeVisited(DijkstraNode node, LinkedList<DijkstraNode> nodes) {
		for (Iterator<DijkstraNode> iter = nodes.iterator(); iter.hasNext(); ) {
			DijkstraNode next = iter.next();
			if (node.getId() == next.getId()) {
				iter.remove();
				return;
			}	
		}
	}

	/**
	 * <p>Returns the path computed by {@link #dijkstraAlghoritm()} to a given node.<br>
	 * The starting and ending node will be included in the path.</p>
	 * <p>This method MUST be called AFTER {{@link #dijkstraAlghoritm()};
	 * @param destId The destination of the path
	 * @return A {@code ArrayList<DijkstraNode>} containing every node in the path
	 */
	public ArrayList<DijkstraNode> getPathTo(int destId) {
		ArrayList<DijkstraNode> out = new ArrayList<>();
		int nextNodeId = destId;
		while (nextNodeId != rootId) {
			DijkstraNode node = allNodes.get(nextNodeId);
			out.add(node);
			
			nextNodeId = prev[nextNodeId];
			if (nextNodeId == UNKNOWN_VALUE)
				return null;
		}
		out.add(allNodes.get(nextNodeId));
		Collections.reverse(out);
		return out;
	}
	
	/**
	 * <p>Wrapper method that returns the path to the last node in the graph
	 * The starting and ending node will be included in the path.</p>
	 * <p>This method MUST be called AFTER {{@link #dijkstraAlghoritm()};
	 * @return A {@code ArrayList<DijkstraNode>} containing every node in the path
	 */
	public ArrayList<DijkstraNode> getPathToLast() {
		return getPathTo(allNodes.size() - 1);
	}
	/**
	 * <p>Private method that returns the unvisited {@code DijkstraNode} closest to the root node
	 * @return A {@code DijkstraNode} object representing the closest unvisited node
	 */
	private DijkstraNode minDist() {
		DijkstraNode minNode = null;
		double minDist = Double.POSITIVE_INFINITY;
		for (DijkstraNode node : unvisitedNodes) {
			if(dist[node.getId()] < minDist) {
				minDist = dist[node.getId()];
				minNode = node;
			}
		}
		if (minDist == Double.POSITIVE_INFINITY)
			minNode = unvisitedNodes.getFirst();
		return minNode;
	}
	
	/**
	 * <p>Returns the distance of the shortest path between the root node and the node corresponding to the given id
	 * @param destId The id of the destination node
	 * @return The distance to the destination node
	 */
	public double getDistanceTo(int destId) {
		return dist[destId];
	}
	
	/**
	 * <p>Returns the distance of the shortest path between the root node and the node corresponding to the given id
	 * @return The distance to the destination node
	 */
	public double getDistanceToLast() {
		return dist[dist.length - 1];
	}
}
