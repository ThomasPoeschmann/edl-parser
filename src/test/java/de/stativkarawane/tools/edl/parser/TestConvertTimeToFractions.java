package de.stativkarawane.tools.edl.parser;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestConvertTimeToFractions {

	@Test
	public void testConvertTimeToFractions() {
		assertEquals("10:02:49.040", Main.convertTimeToFractions("10:02:49:01"));
		assertEquals("10:02:49.480", Main.convertTimeToFractions("10:02:49:12"));
		assertEquals("10:02:49.960", Main.convertTimeToFractions("10:02:49:24"));
		assertEquals("10:02:49.1000", Main.convertTimeToFractions("10:02:49:25"));
	}
}
