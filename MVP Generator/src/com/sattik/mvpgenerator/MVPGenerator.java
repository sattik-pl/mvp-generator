package com.sattik.mvpgenerator;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sattik.mvpgenerator.Utils.*;
import static com.sattik.mvpgenerator.MyStrings.*;

public class MVPGenerator {

	public static void main(String[] args) {
		Map<String, String> properties = 
			getProjectProperties(readFile("properties\\project.properties"));
		printProperties(properties);
		createPackagesAndClasses("dat", properties);
		System.out.println(success);
	}

	public static Map<String, String> getProjectProperties(List<String> data) {
		checkDataCorrectness(data);
		return getMapFromList(data);
	}

	public static void checkDataCorrectness(List<String> data) {
		for (String s : data) {
			if (!s.contains("="))
				error("Error: \"".concat(s.replace("\r\n", ""))
				                 .concat("\" does not contain \"=\"."));
		}
	}

	public static Map<String, String> getMapFromList(List<String> data) {
		return data.stream()
		           .collect(Collectors.toMap(s -> getStringBeforeEq(s), 
		                                     s -> getStringAfterEq(s)));
	}
	
	public static String getStringBeforeEq(String s) {
		return s.substring(0, s.indexOf("="));
	}
	
	public static String getStringAfterEq(String s) {
		return s.substring(s.indexOf("=")+1).replace("\r\n", "");
	}

	public static void printProperties(Map<String, String> properties) {
		System.out.println("properties:");
		System.out.println("-----------");
		System.out.println("path=".concat(properties.get("path")));
		System.out.println("package=".concat(properties.get("package")));
		System.out.println("app=".concat(properties.get("app")));
		System.out.println();
	}

	public static void createPackagesAndClasses(String datPath, Map<String, String> properties) {
		String path = properties.get("path").trim();
		String pckg = properties.get("package").trim().replace(" ", "").toLowerCase();
		String app = properties.get("app").trim().replace(" ", "");
		String packagePath = "\\".concat(pckg).replace(".", "\\").concat("\\");
		pckg = "package ".concat(pckg);
		
		checkIfEmpty(path, incompletePath);
		checkIfEmpty(pckg, incompletePckg);
		checkIfEmpty(app, incompleteApp);
		if (!endsWithSlash(path)) { path += "\\"; }
		app = startWithUpperCase(app);
		checkProject(path);
		
		String srcFolder = path.concat("src").concat(packagePath);
		
		createAppPackage(datPath, pckg, app, srcFolder);
		createModelPackage(datPath, pckg, srcFolder);
		createViewPackage(datPath, pckg, srcFolder);
		createPresenterPackage(datPath, pckg, srcFolder);
		createUtilsPackage(datPath, pckg, app, srcFolder);
		modifyClasspath(path);
		createTestPackages(path, packagePath);
		createResourcesFolder(path);
	}

	public static boolean endsWithSlash(String path) {
		return path.endsWith("\\") || path.endsWith("/");
	}

	public static String startWithUpperCase(String s) {
		return s.substring(0, 1).toUpperCase().concat(s.substring(1));
	}

	public static void checkIfEmpty(String s, String error) {
		if (s.isEmpty()) { error(error); }
	}

	public static void checkProject(String path) {
		File file = new File(path);
		if (!file.exists()) { error(projectNotInitialized); }
		
		file = new File(path.concat("src"));
		if (!file.exists()) { error(projectNotInitialized); }
		
		file = new File(path.concat(".classpath"));
		if (!file.exists()) { error(projectNotInitialized); }
	}

	public static void createAppPackage(String datPath, String pckg, 
			String app, String srcFolder) {
		File dir = new File(srcFolder.concat("\\app"));
		String dest = dir.toString();
		createFolders(dir);
		List<String> lines = readFile(datPath.concat("\\MyApp.java"));
		String contents = generateContents(
			lines, 
			pckg.concat(".app;\r\n\r\n")
				.concat(addImport(pckg, ".view.View;\r\n\r\n")))
			.replace("MyApp", app);
		saveFile(dest.concat("\\").concat(app).concat(".java"), contents);
		generateNewFile(dest, datPath, "\\Contract.java", pckg.concat(".app;\r\n\r\n"));
		
		System.out.println("--> app package generated");
	}

	public static void createModelPackage(String datPath, String pckg, 
			String srcFolder) {
		File dir = new File(srcFolder.concat("\\model"));
		String dest = dir.toString();
		createFolders(dir);
		generateNewFile(dest, datPath, "\\Model.java", pckg.concat(".model;\r\n\r\n")
			.concat(addImport(pckg, ".app.Contract;\r\n\r\n")));
		
		System.out.println("--> model package generated");
	}

	public static void createViewPackage(String datPath, String pckg, 
			String srcFolder) {
		File dir = new File(srcFolder.concat("\\view"));
		String dest = dir.toString();
		createFolders(dir);
		generateNewFile(dest, datPath, "\\View.java", 
			pckg.concat(".view;\r\n\r\n")
				.concat("import java.awt.event.ActionEvent;\r\n")
				.concat("import java.awt.event.ActionListener;\r\n\r\n")
			    .concat(addImport(pckg, ".app.Contract;\r\n"))
			    .concat(addImport(pckg, ".presenter.Presenter;\r\n\r\n")));
		generateNewFile(dest, datPath, "\\UIWindow.java", pckg.concat(".view;\r\n\r\n")
				.concat("import javax.swing.JFrame;\r\n")
				.concat("import javax.swing.JMenu;\r\n")
				.concat("import javax.swing.JMenuBar;\r\n")
				.concat("import javax.swing.JMenuItem;\r\n\r\n")
				.concat(addImportStatic(pckg, ".utils.MyStrings.*;\r\n\r\n")));
		
		System.out.println("--> view package generated");
	}

	public static void createPresenterPackage(String datPath, String pckg, 
			String srcFolder) {
		File dir = new File(srcFolder.concat("\\presenter"));
		String dest = dir.toString();
		createFolders(dir);
		generateNewFile(dest, datPath, "\\Presenter.java", 
			pckg.concat(".presenter;\r\n\r\n")
				.concat(addImport(pckg, ".app.Contract;\r\n"))
				.concat(addImport(pckg, ".model.Model;\r\n\r\n"))
				.concat(addImportStatic(pckg, ".utils.Utils.*;\r\n\r\n"))
				.concat(addImportStatic(pckg, ".utils.MyStrings.*;\r\n\r\n")));
		
		System.out.println("--> presenter package generated");
	}

	public static void createUtilsPackage(String datPath, String pckg, 
			String app, String srcFolder) {
		File dir = new File(srcFolder.concat("\\utils"));
		String dest = dir.toString();
		createFolders(dir);
		generateNewFile(dest, datPath, "\\Utils.java", pckg.concat(".utils;\r\n\r\n"));
		generateNewFile(dest, datPath, "\\Fonts.java", pckg.concat(".utils;\r\n\r\n"));
		generateNewFile(dest, datPath, "\\Colors.java", pckg.concat(".utils;\r\n\r\n"));
		generateNewFile(dest, datPath, "\\Toast.java", pckg.concat(".utils;\r\n\r\n"));
		generateNewFile(dest, datPath, "\\IOUtils.java", pckg.concat(".utils;\r\n\r\n")
				.concat("import java.util.List;\r\n")
				.concat("import java.util.ArrayList;\r\n")
				.concat("import java.util.Scanner;\r\n\r\n")
				.concat("import java.io.File;\r\n")
				.concat("import java.io.FileReader;\r\n")
				.concat("import java.io.BufferedReader;\r\n")
				.concat("import java.io.BufferedWriter;\r\n")
				.concat("import java.io.PrintWriter;\r\n")
				.concat("import java.io.Writer;\r\n")
				.concat("import java.io.OutputStreamWriter;\r\n")
				.concat("import java.io.FileOutputStream;\r\n")
				.concat("import java.io.IOException;\r\n")
				.concat("import java.io.FileNotFoundException;\r\n\r\n")
				.concat(addImportStatic(pckg, ".utils.Utils.*;\r\n"))
				.concat(addImportStatic(pckg, ".utils.MyStrings.*;\r\n\r\n")));
		
		List<String> lines = readFile(datPath.concat("\\MyStrings.java"));
		String contents = generateContents(
			lines, 
			pckg.concat(".utils;\r\n\r\n"))
			.replace("MyApp", app);
		saveFile(dest.concat("\\MyStrings.java"), contents);
		
		System.out.println("--> utils package generated");
	}

	public static void modifyClasspath(String path) {
		List<String> lines = readUTF8File(path.concat(".classpath"));
		String contents = generateContents(lines, "");
		if (contents.contains("<classpathentry kind=\"src\" path=\"test\"/>")) { return; }
		
		contents = contents.replace("<classpathentry kind=\"src\" path=\"src\"/>", 
			"<classpathentry kind=\"src\" path=\"src\"/>\r\n"
			.concat("\t<classpathentry kind=\"src\" path=\"test\"/>\r\n"));
		saveUTF8File(path.concat(".classpath"), contents);
		
		System.out.println("--> .classpath modified");
	}

	public static void createTestPackages(String path, String packagePath) {
		String srcFolder = path.concat("test").concat(packagePath);
		File dir = new File(srcFolder.concat("\\app"));
		createFolders(dir);
		dir = new File(srcFolder.concat("\\model"));
		createFolders(dir);
		dir = new File(srcFolder.concat("\\presenter"));
		createFolders(dir);
		
		System.out.println("--> test packages generated");
	}
	
	public static String addImport(String pckg, String ending) {
		return "import ".concat(pckg.replace("package ", "")).concat(ending);
	}
	
	public static String addImportStatic(String pckg, String ending) {
		return "import static ".concat(pckg.replace("package ", "")).concat(ending);
	}
	
	public static void generateNewFile(String dirPath, String datPath, 
			String filePath, String pckg) {
		String contents = 
				generateContents(readFile(datPath.concat(filePath)), pckg);
		saveFile(dirPath.concat(filePath), contents);
	}
	
	public static String generateContents(List<String> list, String pckg) {
		StringBuilder sb = new StringBuilder(pckg);
		for (String s : list) { sb.append(s); }
		return sb.toString();
	}
	
	public static void createResourcesFolder(String path) {
		createFolders(new File(path.concat("src\\resources")));
		
		System.out.println("--> resources folder created");
	}
	
}
