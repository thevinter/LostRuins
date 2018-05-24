package lostRuins;

import java.util.ArrayList;
/**
 * <p>This class is used to represent the whole map near the Lost Ruins, containing many cities.</p>
 * <p>The size of the map must be specified on the creation of the object.<br>
 * Therefore all of the cities must have a unique id field with a value included between 0 and such given size.<br>
 * The number of inserted cities must also be exactly the one given by size.
 */
public class RuinsMap {
	
	private static final int TONATIUH = 0; 
	private static final int METZTLI = 1; 
	
	private int size;
	private City[] map;
	/**
	 * <p>Constructor that creates a new map with a given size
	 * @param size The size of the new map
	 */
	public RuinsMap(int size) {
		map = new City[size];
		this.size = size;
	}

	/**
	 * <p>Method to add a new city<p>
	 * <p>The {@code id} field of the {@code City} object must be unique in this map,
	 * as the repetition of id will cause overwriting and data loss
	 * @param newCity The {@code City} object to be added to the map
	 */
	public void add(City newCity) {
		map[newCity.getId()] = newCity;
	}
	
	/**
	 * <p>Returns the size of the map
	 * @return The number of total cities that will be in the map once it is completely filled
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * <p>Converts all of the data inside the city to an {@code ArrayList<DijkstraNode>}.<br>
	 * This method will also compute the distances using team Tonatiuh fuel consumption (proportional to the cartesian distance).</p>
	 * <p>This data will be usable by the {@code Dijkstra} class to compute the shortest path for the team Tonatiuh<br>
	 * The resulting {@code ArrayList<DijkstraNode>} should then be used as a parameter in the constructor for the {@code Dijkstra} class
	 * @return An {@code ArrayList<DijkstraNode>} containing the structure of the map
	 */
	public ArrayList<DijkstraNode> getTonatiuhDijkstra() {
		return getDijkstra(TONATIUH);
	}
	
	/**
	 * <p>Converts all of the data inside the city to an {@code ArrayList<DijkstraNode>}.<br>
	 * This method will also compute the distances using team Metztli fuel consumption (proportional to the height difference).</p>
	 * <p>This data will be usable by the {@code Dijkstra} class to compute the shortest path for team Metztli<br>
	 * The resulting {@code ArrayList<DijkstraNode>} should then be used as a parameter in the constructor for the {@code Dijkstra} class
	 * @return An {@code ArrayList<DijkstraNode>} containing the structure of the map
	 */
	public ArrayList<DijkstraNode> getMetztliDijkstra() {
		return getDijkstra(METZTLI);
	}
	
	/**
	 * <p>Inner private method that actually converts the map into  {@code Dijkstra} class readable dataset for both teams<br>
	 * This method is called by both {@link #getTonatiuhDijkstra()} and {@link #getMetztliDijkstra()} methods
	 * @param type An integer value representing the team ({@link #TONATIUH} and {@link #METZTLI}) 
	 * @return An {@code ArrayList<DijkstraNode>} containing the structure of the map
	 */
	private ArrayList<DijkstraNode> getDijkstra(int type) {
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
	 * <p>Private method to compute the metztlic distance between two cities (height difference)
	 * @param city1 First city
	 * @param city2 Second city
	 * @return The Metztli distance between two cities
	 */
	private double getMetztliDistance(City city1, City city2) {
		return Math.abs(city1.getHeight() - city2.getHeight());
	}

	/**
	 * <p>Private method to compute the tonatiuhan distance between two cities (cartesian distance)
	 * @param city1 First city
	 * @param city2 Second city
	 * @return The Tonatiuhan distance between the cities (cartesian distance)
	 */
	private double getTonatiuhDistance(City city1, City city2) {
		double distX = city1.getX() - city2.getX();
		double distY = city1.getY() - city2.getY();
		
		return Math.sqrt( Math.pow(distX, 2) + Math.pow(distY, 2) );
	}
}
