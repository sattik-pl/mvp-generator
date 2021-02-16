package com.sattik.mvpgenerator;

public final class MyStrings {
	private MyStrings() { throw new AssertionError(); }
	
	static String copyingError = "Error while copying file ";
	static String readingError = "Error while reading file ";
	static String savingError = "Error while saving file ";
	
	static String incompletePath = "Error: incomplete path property.";
	static String incompletePckg = "Error: incomplete package property.";
	static String incompleteApp = "Error: incomplete app property.";
	
	static String success = "\nMVP generated successfully.\n";

}
