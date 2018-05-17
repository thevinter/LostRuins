package lostRuins;

import java.util.ArrayList;

public class City {
	private String label;
	
	private int id;
	
	private int x;
	private int y;
	private int h;
	
	private ArrayList<Integer> linkedCities;
	
	public City() {
		linkedCities = new ArrayList<>();
	}
	
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return h;
	}

	public void setHeight(int h) {
		this.h = h;
	}

	public void addLink(int i) {
		linkedCities.add(i);
	}
}
