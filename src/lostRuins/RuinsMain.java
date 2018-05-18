package lostRuins;

public class RuinsMain {

	public static void main(String[] args) {
		int numberFile = 10000;
		
		Reader reader = new Reader();
		reader.setFilePath(String.format("./input/PgAr_Map_%d.xml", numberFile));
		reader.readAll();
		
		RuinsMap map = reader.returnData();
		
		System.out.println(String.format("Printing tonatiuh %d (dist)", numberFile));
		{
			Dijkstra algorithm = new Dijkstra(map.getTonatiuhDijkstra());
			algorithm.dijkstraAlghoritm();
			
			for (DijkstraNode node : algorithm.getPathTo(map.getSize() - 1)) {
				System.out.println(node);
			}
			System.out.println("Dist: " + algorithm.getDistance(map.getSize() - 1));
		}
		
		System.out.println(String.format("Printing metztli %d (height)", numberFile));
		{
			Dijkstra algorithm = new Dijkstra(map.getMetztliDijkstra());
			algorithm.dijkstraAlghoritm();
			
			for (DijkstraNode node : algorithm.getPathTo(map.getSize() - 1)) {
				System.out.println(node);
			}
			System.out.println("Dist: " + algorithm.getDistance(map.getSize() - 1));
		}
	}

}
