package lostRuins;

public class RuinsMain {

	public static void main(String[] args) {
		Reader r = new Reader();
		r.setFilePath("./input/PgAr_Map_12.xml");
		r.readAll();
		
		RuinsMap m = r.returnData();
	}

}
