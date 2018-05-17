package lostRuins;

import java.util.ArrayList;

public class RuinsMap {
	
	private static final int TONATIUH = 0; 
	private static final int METZTLI = 1; 
	
	private int size;
	private City[] map;
	
	public RuinsMap(int size) {
		map = new City[size];
		this.size = size;
	}

	public void add(City newCity) {
		map[newCity.getId()] = newCity;
	}
	
	public int getSize() {
		return size;
	}
	
	public ArrayList<DijkstraNode> getTonatiuhDijkstra() {
		return getDijkstra(TONATIUH);
	}
	
	public ArrayList<DijkstraNode> getMetztliDijkstra() {
		return getDijkstra(METZTLI);
	}
	
	public ArrayList<DijkstraNode> getDijkstra(int type) {
		if (type != TONATIUH && type != METZTLI) return null;
		ArrayList<DijkstraNode> out = new ArrayList<>();
		
		for (City city : map) {
			DijkstraNode newNode = new DijkstraNode(city.getId(), city.getLabel());

			for (Integer destId : city.getLinks()) {
				double weight = 0; 
				switch (type) {
				case TONATIUH:
					weight = getTonatiuhDistance(city, map[destId]);
					break;
				case METZTLI:
					weight = getMetztliDistance(city, map[destId]);
					break;
				}
				DijkstraEdge newEdge = new DijkstraEdge(city.getId(), destId, weight);
				
				newNode.addEdge(newEdge);
				
			}
			
			out.add(newNode);
		}
		
		return out;
	}

	/**
	 * Returns the Metztli distance between two cities (difference of heights)
	 * @param city First city
	 * @param city2 Second city
	 * @return The Metztli distance between two cities
	 */
	private double getMetztliDistance(City city, City city2) {
		return Math.abs(city.getHeight() - city2.getHeight());
	}

	/**
	 * Returns the Tonatiuhan distance between two cities (cartesian distance)
	 * @param city First city
	 * @param city2 Second city
	 * @return The Tonatiuhan distance between the cities
	 */
	private double getTonatiuhDistance(City city, City city2) {
		double distX = city.getX() - city2.getX();
		double distY = city.getY() - city2.getY();
		
		return Math.sqrt( Math.pow(distX, 2) + Math.pow(distY, 2) );
	}
}
