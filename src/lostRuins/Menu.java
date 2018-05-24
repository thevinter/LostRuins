package lostRuins;

import java.util.Scanner;

/**
 * <p>This class is used to print and handle a text menu in the console.</p>
 * <p>It automatically prints the options and lets the user choose, while also handling possible mistakes 
 */
public class Menu {
	private Scanner scanner = new Scanner(System.in).useDelimiter(System.getProperty("line.separator"));
	private String title;
	private String[] options;
	
	/**
	 * <p>Default constructor (the other parameters must be set with the setters before calling the choose method).
	 */
	public Menu() {
		this.title = null;
		this.options = null;
	}
	
	/**
	 * <p>Constructor used to set the title and the options
	 * @param title A string containing the title of the menu
	 * @param options An array of strings containing the various choices of the menu
	 */
	public Menu(String title, String[] options) {
		this.title = title;
		this.options = options;
	}
	
	/**
	 * <p>Constructor used to set the title and the options.<br>
	 * The options is an array of generics, that will automatically be converted to String using {@code String::valueOf}
	 * @param title A String containing the title of the menu
	 * @param options An array of generics containing the various choices of the menu
	 */
	public <T> Menu(String title, T[] options) {
		String[] sOptions = new String[options.length];
		for (int i = 0; i < options.length; i++)
			sOptions[i] = String.valueOf(options[i]);
		this.title = title;
		this.options = sOptions;
	}
	
	/**
	 * <p>Setter for the title attribute.
	 * @param title The new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * <p>Setter for the options attribute.
	 * @param options The new options
	 */
	public void setOptions(String[] options) {
		this.options = options;
	}
	
	/**
	 * <p>This method prints the menu and allows the user to express their choice.<br>
	 * It checks automatically for the right data type and value.
	 * @return The integer value of the choice of the user
	 */
	public int choose() {
		System.out.println('\n');
		System.out.println(title);
		int i = 0;
		int out = 0;
		boolean error = true;
		for (String option : options) {
			System.out.println(i++ + ") " + option);
		}
		do {
			error = true;
			try {
				System.out.print("Make your choice > ");
				out = scanner.nextInt();
				error = false;
			}
			catch (Exception e) {
				System.out.println("Wrong data type: expected int - Try again.");
				scanner.next();
			}
		} while (out < 0 || out >= i || error);
		return out;
	}
}
