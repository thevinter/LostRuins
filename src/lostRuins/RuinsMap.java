package lostRuins;

import java.util.ArrayList;

public class RuinsMap {
	
	private City[] map;
	
	public RuinsMap(int size) {
		map = new City[size];
	}

	public void add(City newCity) {
		map[newCity.getId()] = newCity;
	}
}
