package org.tinyheb.core;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tinyheb.core.Patron;

public class PatronTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testPatronIntStringString() {
		Patron testPatron = new Patron(2, "Vorname", "Nachname");
		assertEquals(2, testPatron.getId());
		assertEquals("Vorname", testPatron.getFirstname());
		assertEquals("Nachname", testPatron.getLastname());
	}

}
