package com.sattik.mvpgenerator;

import static org.junit.Assert.assertEquals;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static com.sattik.mvpgenerator.Utils.*;

class UtilsTest {
	
	/*
	 * my saving methods do not have any logic, so there is no need to test them
	 */

	@Test
	void should_return_list_from_reader() {
		List<String> list = new ArrayList<String>(Arrays.asList("abc=xyz\r\n", "xyz=abc\r\n"));
		String name = "abc=xyz\r\nxyz=abc\r\n";
		Reader reader = new StringReader(name);
		assertEquals(list, read(name, reader));
	}
}
