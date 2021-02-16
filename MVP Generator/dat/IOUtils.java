public final class IOUtils {
	private IOUtils() { throw new AssertionError(); }
	
	public static List<String> readFile(String name) {
		List<String> list = new ArrayList<String>();
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(name))) {
			while ((line = br.readLine()) != null)
				list.add(line); }
		catch (IOException e) { 
			error(readingError.concat("\"").concat(name).concat("\"."));
			return new ArrayList<String>(); }
		return list;
	}
	
	public static List<String> readUTF8File(String name) {
		List<String> list = new ArrayList<String>();
		try (Scanner skaner = new Scanner(new File(name), "UTF-8"); ) { 
			while (skaner.hasNextLine())
				list.add(skaner.nextLine()); }
		catch (FileNotFoundException ex) {
			error(readingError.concat("\"").concat(name).concat("\".")); 
			return new ArrayList<String>(); }
		return list;
	}
	
	public static void saveFile(String name, String contents) {
		try (PrintWriter pw = new PrintWriter(name)) {
			pw.print(contents); }
		catch (FileNotFoundException e) {
			error(savingError.concat("\"").concat(name).concat("\".")); }
	}
	
	public static void saveUTF8File(String name, String contents) {
		try (Writer bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(name), "UTF-8"))) {
			bw.write(contents);
			bw.flush(); }
		catch (IOException e) { 
			error(savingError.concat("\"").concat(name).concat("\".")); }
	}
}