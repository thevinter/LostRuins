package lostRuins;

import java.util.ArrayList;

public class RuinsMain {

	private static final String MENU_TITLE = "Welcome to Route to the Ruins! How many cities does your reality have?";
	private static final Integer[] VALID_NUMBER_OF_CITIES = {5, 12, 50, 200, 2000, 10000};
	private static final String GENERIC_FILE_PATH = "./input/PgAr_Map_%d.xml";
	private static final String OUTPUT_FILE_PATH = "./output/Routes.xml";

	private static final String SUCCESSFUL_PRINT_MESSAGE = "Printed to " + OUTPUT_FILE_PATH;
	private static final String FAILED_PRINT_MESSAGE = "There's been an error while printing the file";
	
	private static final String TONATIUH = "Tonatiuh";
	private static final String METZTLI = "Metztli";
	
	
	private static final String ROOT_TAG = "solution";
	
	private static final String CITY_TAG = "city";
	private static final String CITY_ID_ATT = "id";
	private static final String CITY_NAME_ATT = "name";
	
	private static final String TEAM_TAG = "route";
	private static final String TEAM_NAME_ATT = "team";
	private static final String TEAM_COST_ATT = "cost";
	private static final String TEAM_CITIES_ATT = "cities";
	
	
	public static void main(String[] args) {
		
		// asks the user which file to use
		Menu menu = new Menu(MENU_TITLE, VALID_NUMBER_OF_CITIES);
		int numberFile = VALID_NUMBER_OF_CITIES[menu.choose()];
		
		// XML input to a RuinsMap object
		Reader reader = new Reader();
		reader.setFilePath(String.format(GENERIC_FILE_PATH, numberFile));
		reader.readAll();
		RuinsMap map = reader.returnData();
		
		// executes dijkstra and stores the result
		WriterTag sol = new WriterTag(ROOT_TAG);
		// tonatiuh
		Dijkstra algorithm = new Dijkstra(map.getTonatiuhDijkstra());
		sol.addSubTag(runDijkstraToWritable(algorithm, TONATIUH));
		// metztli
		algorithm = new Dijkstra(map.getMetztliDijkstra());
		sol.addSubTag(runDijkstraToWritable(algorithm, METZTLI));
		
		// writing on file
		Writer writer = new Writer(OUTPUT_FILE_PATH, sol);
		writer.init();
		System.out.println( writer.run() ? SUCCESSFUL_PRINT_MESSAGE : FAILED_PRINT_MESSAGE );
	}

	private static void dijkstraIntoWritable(ArrayList<DijkstraNode> nodes, WriterTag container, String nodeTag) {
		for (DijkstraNode n : nodes) {
			WriterTag city = new WriterTag(nodeTag);
			city.addAttribute(CITY_ID_ATT, n.getId());
			city.addAttribute(CITY_NAME_ATT, n.getLabel());
			
			container.addSubTag(city);
		}
	}
	
	private static WriterTag runDijkstraToWritable(Dijkstra dijkstra, String teamName) {
		dijkstra.dijkstraAlghoritm();
		ArrayList<DijkstraNode> dijPath = dijkstra.getPathToLast();
		WriterTag route = new WriterTag(TEAM_TAG);
		route.addAttribute(TEAM_NAME_ATT, teamName);
		route.addAttribute(TEAM_COST_ATT, dijkstra.getDistanceToLast());
		route.addAttribute(TEAM_CITIES_ATT, dijPath.size());
		dijkstraIntoWritable(dijPath, route, CITY_TAG);
		
		return route;
	}
}
