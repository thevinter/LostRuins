package lostRuins;

import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class Writer {
	
	private String path;
	private XMLOutputFactory factory;
	private XMLStreamWriter writer;
	private WriterTag rootTag;
	
	public Writer(String path, WriterTag rootTag) {
		this.path = path;
		this.rootTag = rootTag;
	}
		
	public boolean init() {
		factory = XMLOutputFactory.newInstance();
		try {
			writer = factory.createXMLStreamWriter(new FileOutputStream(path), "utf-8");
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean run() {
		try {
			writer.writeStartDocument();
	
			if (!printTag(rootTag))
				return false;
			
			writer.writeEndDocument();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean printTag(WriterTag tag) {
		try {
			
			writer.writeStartElement(tag.getName());
			
			while (tag.selectNextAttribute()) {
				writer.writeAttribute(tag.getAttributeName(), tag.getAttributeValue());
			}
			
			for (WriterTag subTag : tag.getSubTags()) {
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
