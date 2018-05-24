package lostRuins;

import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/**
 * <p>This class is used to write into a XML file.<br>
 * It works alongside the {@code XMLWriterTag} class to print an XML file
 */
public class XMLWriter {
	
	private String path;
	private XMLOutputFactory factory;
	private XMLStreamWriter writer;
	private XMLWriterTag rootTag;
	/**
	 * <p>Constructor that creates a new instance of the writer, specifying the path of the output file and a {@code WriterTag} representing the root tag 
	 * @param path The relative path of the output file
	 * @param rootTag The root tag
	 */
	public XMLWriter(String path, XMLWriterTag rootTag) {
		this.path = path;
		this.rootTag = rootTag;
	}
	
	/**
	 * <p>Initializes the inner variables for the printer<br>
	 * This method MUST be called BEFORE the {@link #run()} method
	 * @return True if successful, false if anything fails
	 */
	public boolean init() {
		factory = XMLOutputFactory.newInstance();
		try {
			writer = factory.createXMLStreamWriter(new FileOutputStream(path), "utf-8");
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * <p>This method is used to actually write the data in the new XML document
	 * @return True if successful, false if anything fails
	 */
	public boolean run() {
		try {
			writer.writeStartDocument();
	
			if (!printTag(rootTag))
				return false;
			
			writer.writeEndDocument();
			writer.flush();
			writer.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * <p>Private method used to print to file a single XML tag.<br>
	 * It is called recursively in order to print all of the nested tags.
	 * @param tag The {@code XMLWriterTag} to be printed
	 * @return True if successful, false if anything fails
	 */
	private boolean printTag(XMLWriterTag tag) {
		try {
			
			writer.writeStartElement(tag.getName());
			
			while (tag.selectNextAttribute()) {
				writer.writeAttribute(tag.getAttributeName(), tag.getAttributeValue());
			}
			if (tag.getText() != null)
				writer.writeCharacters(tag.getText());
			
			for (XMLWriterTag subTag : tag.getSubTags()) {
				if (!printTag(subTag))
					return false;
			}
			
			writer.writeEndElement();
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
}
