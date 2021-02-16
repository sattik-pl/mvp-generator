package com.sattik.mvpgenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;

import static com.sattik.mvpgenerator.MVPGenerator.*;

class MVPGeneratorTest {
	
	private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static PrintStream originalOut = System.out;

	@BeforeAll
	public static void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}
	
	@AfterAll
	public static void restoreStreams() {
		System.setOut(originalOut);
	}

	@Test
	@ExpectSystemExitWithStatus(1)
	void should_print_error_and_exit_when_no_equals_sign() {
		List<String> list = new ArrayList<String>(Arrays.asList("abc-xyz\r\n"));
		checkDataCorrectness(list);
	    assertEquals("Error: \"abc-xyz\" does not contain \"=\".\n", outContent.toString());
	}

	@Test
	void should_return_map_from_list() {
		List<String> list = new ArrayList<String>(Arrays.asList("abc=xyz\r\n"));
		Map<String, String> map = new HashMap<String, String>();
		map.put("abc", "xyz");
		assertEquals(map, getMapFromList(list));
	}

	@Test
	void should_return_string_before_equals_sign() {
		assertEquals("abc", getStringBeforeEq("abc=xyz"));
	}

	@Test
	void should_return_string_after_equals_sign() {
		assertEquals("xyz", getStringAfterEq("abc=xyz"));
	}

	@Test
	void should_return_true_when_ends_with_backslash() {
		assertTrue(endsWithSlash("abc\\"));
	}

	@Test
	void should_return_true_when_ends_with_slash() {
		assertTrue(endsWithSlash("abc/"));
	}

	@Test
	void should_return_false_when_no_backslash_or_slash() {
		assertFalse(endsWithSlash("abc"));
	}

	@Test
	void should_return_string_with_first_letter_in_upper_case() {
		assertEquals("Abc", startWithUpperCase("abc"));
	}

	@Test
	@ExpectSystemExitWithStatus(1)
	void should_print_error_and_exit_when_empty_string() {
		checkIfEmpty("", "error");
	    assertEquals("error\n", outContent.toString());
	}

	@Test
	void should_replace_package_with_import_and_add_ending() {
		String pckg = "package abc";
		String ending = "XYZ";
		assertEquals("import abcXYZ", addImport(pckg, ending));
	}

	@Test
	void should_replace_package_with_import_static_and_add_ending() {
		String pckg = "package abc";
		String ending = "XYZ";
		assertEquals("import static abcXYZ", addImportStatic(pckg, ending));
	}

	@Test
	void should_return_contents_starting_with_package_and_containing_elements_from_list() {
		List<String> list = new ArrayList<String>(Arrays.asList("abc\r\n", "xyz\r\n"));
		String pckg = "package abc\r\n\r\n";
		String contents = "package abc\r\n\r\nabc\r\nxyz\r\n";
		assertEquals(contents, generateContents(list, pckg));
	}
}
