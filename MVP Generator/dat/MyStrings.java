public final class MyStrings {
	private MyStrings() { throw new AssertionError(); }
	
	public static String title = "MyApp";
	public static String menuAbout = "About";
	public static String version = "ver.";
	
	public static String readingError = "Error while reading file:\n";
	public static String savingError = "Error while saving file:\n";
	
	public static String about = 
		title.concat("\nCopyright © Name 2021\n")
		     .concat(version).concat(" 1.0\n\n");
}
