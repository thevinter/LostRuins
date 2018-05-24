package lostRuins;

import java.util.ArrayList;

/**
 * <p>This class is used to represent a single city.<p>
 * <p>It contains data about a city, such as the name, the position, the height and the neighbouring cities.
 */
public class City {
	private String label;
	
	private int id;
	
	private int x;
	private int y;
	private int h;
	
	private ArrayList<Integer> linkedCities;
	
	/**
	 * <p>Basic constructor for the a city
	 */
	public City() {
		linkedCities = new ArrayList<>();
	}
	
	/**
	 * <p>Getter for the label (the name) of the city
	 * @return The name of the city
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * <p>Setter for the label (the name) of the city
	 * @param label The new name of the city
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * <p>Getter for the identifying id of the city
	 * @return The id of the city
	 */
	public int getId() {
		return id;
	}

	/**
	 * <p>Setter for the identifying id of the city
	 * @param id The new id of the city
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * <p>Getter for the X-coordinate of the city
	 * @return The X-coordinate of the city
	 */
	public int getX() {
		return x;
	}

	/**
	 * <p>Setter for the X-coordinate of the city
	 * @return The new X-coordinate of the city
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * <p>Getter for the Y-coordinate of the city
	 * @return The Y-coordinate of the city
	 */
	public int getY() {
		return y;
	}

	/**
	 * <p>Setter for the Y-coordinate of the city
	 * @return The new Y-coordinate of the city
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * <p>Getter for the height of the city
	 * @return The height of the city
	 */
	public int getHeight() {
		return h;
	}

	/**
	 * <p>Setter for the height of the city
	 * @return The new height of the city
	 */
	public void setHeight(int h) {
		this.h = h;
	}
	
	/**
	 * <p>Getter for all of the destinations of the links from this city
	 * @return An {@code ArrayList<Integer>} containing the ids of all of the cities directly reachable from this one
	 */
	public ArrayList<Integer> getLinks() {
		return new ArrayList<>(linkedCities);
	}

	/**
	 * <p>Adds a new link to a given city
	 * @param i The id of the destination of the new link (a City)
	 */
	public void addLink(int i) {
		linkedCities.add(i);
	}
}
