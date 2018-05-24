package lostRuins;

import java.util.ArrayList;
/**
 * <p>Class that holds the {@link #main(String[])} method
 */
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
	
	/**
	 * <p>The {@code main} method 
	 * @param args The args
	 */
	public static void main(String[] args) {
		
		// asks the user which file to use
		Menu menu = new Menu(MENU_TITLE, VALID_NUMBER_OF_CITIES);
		int numberFile = VALID_NUMBER_OF_CITIES[menu.choose()];

		long initTime = System.nanoTime();
		// XML input to a RuinsMap object
		RuinsMap map = readMap(String.format(GENERIC_FILE_PATH, numberFile));
		
		// executes dijkstra and stores the result
		XMLWriterTag sol = new XMLWriterTag(ROOT_TAG);
		// tonatiuh
		Dijkstra algorithm = new Dijkstra(map.getTonatiuhDijkstra());
		sol.addSubTag(runDijkstraToWritable(algorithm, TONATIUH));
		// metztli
		algorithm = new Dijkstra(map.getMetztliDijkstra());
		sol.addSubTag(runDijkstraToWritable(algorithm, METZTLI));
		
		// writing on file
		XMLWriter writer = new XMLWriter(OUTPUT_FILE_PATH, sol);
		writer.init();
		System.out.println( writer.run() ? SUCCESSFUL_PRINT_MESSAGE : FAILED_PRINT_MESSAGE );
		System.out.printf("(Program executed in %dms)%n", (System.nanoTime() - initTime) / 1000000);
	}
	
	/**
	 * <p>This method reads the input file and returns a {@code RuinsMap} object containing the data
	 * @param path The path of the input file
	 * @return The map
	 */
	public static RuinsMap readMap(String path) {
		Reader reader = new Reader();
		reader.setFilePath(path);
		reader.readAll();
		return reader.returnData();
	}

	/**
	 * <p>Writes a dijkstra path into a {@code XMLWriterTag}
	 * @param nodes The path
	 * @param container The XMLWriterTag that will contain the final data
	 * @param nodeTag Tha name of every node tag
	 */
	public static void dijkstraIntoWritable(ArrayList<DijkstraNode> nodes, XMLWriterTag container, String nodeTag) {
		for (DijkstraNode n : nodes) {
			XMLWriterTag city = new XMLWriterTag(nodeTag);
			city.addAttribute(CITY_ID_ATT, n.getId());
			city.addAttribute(CITY_NAME_ATT, n.getLabel());
			
			container.addSubTag(city);
		}
	}
	
	/**
	 * <p>Runs Dijkstra's algorithm on a given {@code Dijkstra] class
	 * @param dijkstra The initialized {@code Dijkstra} object
	 * @param teamName The name of the team (to be printed in the file)
	 * @return A {@code XMLWriterTag} containing the desired path
	 */
	public static XMLWriterTag runDijkstraToWritable(Dijkstra dijkstra, String teamName) {
		dijkstra.dijkstraAlghoritm();
		ArrayList<DijkstraNode> dijPath = dijkstra.getPathToLast();
		XMLWriterTag route = new XMLWriterTag(TEAM_TAG);
		route.addAttribute(TEAM_NAME_ATT, teamName);
		route.addAttribute(TEAM_COST_ATT, dijkstra.getDistanceToLast());
		route.addAttribute(TEAM_CITIES_ATT, dijPath.size());
		dijkstraIntoWritable(dijPath, route, CITY_TAG);
		
		return route;
	}
}
