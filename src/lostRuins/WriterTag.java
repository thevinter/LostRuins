package lostRuins;

import java.util.ArrayList;

public class WriterTag {
	private String name;
	private ArrayList<Attribute> attributes;
	private String text;
	private ArrayList<WriterTag> subTags;
	private int activeAttribute;
	
	public WriterTag(String name) {
		this.name = name;
		attributes = new ArrayList<>();
		text = null;
		subTags = new ArrayList<>();
		activeAttribute = -1;
	}
	
	public void addAttribute(String name, String content) {
		attributes.add(new Attribute(name, content));
	}

	public <T> void addAttribute(T name, T content) {
		String sName = String.valueOf(name);
		String sContent = String.valueOf(content);
		addAttribute(sName, sContent);
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public void addSubTag(WriterTag tag) {
		subTags.add(tag);
	}

	
	
	public String getName() {
		return name;
	}
	
	public boolean selectNextAttribute() {
		if (++activeAttribute < attributes.size())
			return true;
		return false;
	}
	
	public String getAttributeName() {
		if (activeAttribute >= attributes.size())
			return null;
		
		return attributes.get(activeAttribute).getName();
	}
		
	public String getAttributeValue() {
		if (activeAttribute >= attributes.size())
			return null;
		
		return attributes.get(activeAttribute).getContent();
	}
	
	public ArrayList<WriterTag> getSubTags() {
		return new ArrayList<>(subTags);
	}

	public String getText() {
		return text;
	}

	
	private class Attribute {
		private String name;
		private String content;
		
		public Attribute(String name, String content) {
			this.name = name;
			this.content = content;
		}

		public String getName() {
			return this.name;
		}

		public String getContent() {
			return this.content;
		}

	}
}
