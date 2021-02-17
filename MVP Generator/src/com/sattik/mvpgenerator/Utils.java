package com.sattik.mvpgenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import static com.sattik.mvpgenerator.MyStrings.*;

public final class Utils {
	private Utils() { throw new AssertionError(); }
	
	public static void createFolders(File dir) {
		if (!dir.exists()) { dir.mkdirs(); }
		if (dir.list().length != 0) { error(notEmptyFolders); }
	}
	
	public static List<String> readFile(String name) {
		List<String> list = new ArrayList<String>();
		try { list = read(name, new FileReader(name)); }
		catch (FileNotFoundException e) { error(readingError.concat("\"").concat(name).concat("\".")); }
		return list;
	}
	
	// catching Exception because there might be a lot of different exceptions
	// e.g. concerning Charset, but I do not need to handle them separately
	public static List<String> readUTF8File(String name) {
		List<String> list = new ArrayList<String>();
		try { list = read(name, new InputStreamReader(new FileInputStream(
				new File(name)), "UTF-8")); }
		catch (Exception e) { error(readingError.concat("\"").concat(name).concat("\".")); }
		return list;
	}
	
	public static List<String> read(String name, Reader reader) {
		List<String> list = new ArrayList<String>();
		String line = "";
		try (BufferedReader br = new BufferedReader(reader)) {
			while ((line = br.readLine()) != null)
				list.add(line.concat("\r\n")); }
		catch (IOException e) { error(readingError.concat("\"").concat(name).concat("\".")); }
		return list;
	}
	
	public static void saveFile(String name, String contents) {
		try (PrintWriter pw = new PrintWriter(name)) {
			pw.print(contents); }
		catch (Exception e) { error(savingError.concat("\"").concat(name).concat("\".")); }
	}
	
	public static void saveUTF8File(String name, String contents) {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(name), "UTF-8"))) {
			bw.write(contents);
			bw.flush(); }
		catch (IOException e) { error(savingError.concat("\"").concat(name).concat("\".")); }
	}
	
	public static void error(String error) {
		System.out.println(error);
		System.exit(1);
	}
}
