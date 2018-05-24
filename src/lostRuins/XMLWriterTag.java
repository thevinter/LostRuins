package lostRuins;

import java.util.ArrayList;

/**
 * <p>This class is used to represent a single XML tag to be printed<br>
 * This class is functional to the {@code Writer} class 
 */
public class XMLWriterTag {
	private String name;
	private ArrayList<Attribute> attributes;
	private String text;
	private ArrayList<XMLWriterTag> subTags;
	private int activeAttribute;
	
	/**
	 * <p>Initializes a {@code XMLWriterTag} object with a given name
	 * @param name The name of the tag
	 */
	public XMLWriterTag(String name) {
		this.name = name;
		attributes = new ArrayList<>();
		text = null;
		subTags = new ArrayList<>();
		activeAttribute = -1;
	}
	
	/**
	 * <p>Adds a new attribute to this tag
	 * @param name The name of the new attribute
	 * @param content The text contained in the attribute
	 */
	public void addAttribute(String name, String content) {
		attributes.add(new Attribute(name, content));
	}
	
	/**
	 * <p>Adds a new attribute to this tag, using generics
	 * @param name The name of the new attribute ({@code String::valueOf} will be called on this})
	 * @param content The text contained in the attribute ({@code String::valueOf} will be called on this})
	 */
	public <T,U> void addAttribute(T name, U content) {
		String sName = String.valueOf(name);
		String sContent = String.valueOf(content);
		addAttribute(sName, sContent);
	}
	
	/**
	 * <p>Sets the text of the tag to a given value
	 * @param text The new text of the tag
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * <p>Adds a new sub-tag to this one
	 * @param tag The new sub-tag
	 */
	public void addSubTag(XMLWriterTag tag) {
		subTags.add(tag);
	}
	
	
	/**
	 * <p>Getter for the name of the tag
	 * @return The name of the tag
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>Selects the next attribute and returns true if there's one available<br>
	 * Use alongside {@link #getAttributeName()} and {{@link #getAttributeValue()}</p>
	 * <p>The first call of this method will select the first attribute in the list
	 * @return True if the next attribute exists, false otherwise
	 */
	public boolean selectNextAttribute() {
		if (++activeAttribute < attributes.size())
			return true;
		return false;
	}
	
	/**
	 * <p>Returns the name of the selected attribute
	 * @return The name of the selected attribute
	 */
	public String getAttributeName() {
		if (activeAttribute >= attributes.size())
			return null;
		
		return attributes.get(activeAttribute).getName();
	}
		
	/**
	 * <p>Returns the value of the selected attribute
	 * @return The value of the selected attribute
	 */
	public String getAttributeValue() {
		if (activeAttribute >= attributes.size())
			return null;
		
		return attributes.get(activeAttribute).getContent();
	}
	
	/**
	 * <p>Getter for the list of subtags of this tag
	 * @return An {@code ArrayList<XMLWriterTag>} containing all of the sub-tags
	 */
	public ArrayList<XMLWriterTag> getSubTags() {
		return new ArrayList<>(subTags);
	}

	/**
	 * <p>Getter for the plain text of the tag
	 * @return The text of the tag
	 */
	public String getText() {
		return text;
	}

	/**
	 * <p>Private class to represent a single attribute in a tag
	 */
	private class Attribute {
		private String name;
		private String content;
		
		/**
		 * <p>Creates a new attribute, with a given name and content
		 * @param name The name of the attribute
		 * @param content The value of the attribute
		 */
		public Attribute(String name, String content) {
			this.name = name;
			this.content = content;
		}

		/**
		 * <p>Getter for the name of the attribute
		 * @return The name of the attribute
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * <p>Getter for the value of the attribute
		 * @return The value of the attribute
		 */
		public String getContent() {
			return this.content;
		}

	}
}
