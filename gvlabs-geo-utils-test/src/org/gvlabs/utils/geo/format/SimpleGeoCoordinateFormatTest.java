/**
 * 
 */
package org.gvlabs.utils.geo.format;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.gvlabs.utils.geo.GeoType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * SimpleGeoCoordinateFormat Test Case
 * 
 * @author Thiago Galbiatti Vespa
 *
 */
public class SimpleGeoCoordinateFormatTest {

	private SimpleGeoCoordinateFormat formatterLat = null;
	private SimpleGeoCoordinateFormat formatterLong = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		formatterLat = new SimpleGeoCoordinateFormat(GeoType.LATITUDE);
		formatterLong = new SimpleGeoCoordinateFormat(GeoType.LONGITUDE);
	}
	

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.gvlabs.utils.geo.format.SimpleGeoCoordinateFormat#format(java.lang.Object, java.lang.StringBuffer, java.text.FieldPosition)}.
	 */
	@Test
	public void testFormatObjectStringBufferFieldPosition() {
		assertEquals("1º 30\" 36' N", formatterLat.format(1.51));
		assertEquals("45º 55\" 12' W", formatterLong.format(-45.92));
		assertEquals("12º 36\" 54' S", formatterLat.format(-12.615));
		assertEquals("78º 12\" 36' E", formatterLong.format(78.21));
	}

	/**
	 * Test method for {@link org.gvlabs.utils.geo.format.SimpleGeoCoordinateFormat#parse(java.lang.String)}.
	 * @throws ParseException 
	 */
	@Test
	public void testParseObjectStringParsePosition() throws ParseException {
		assertEquals(1.51, formatterLat.parseObject("1º 30\" 36' N"));
		assertEquals(-45.92, formatterLong.parseObject("45º 55\" 12' W"));
		assertEquals(-12.615, formatterLat.parseObject("12º 36\" 54' S"));
		assertEquals(78.21, formatterLong.parseObject("78º 12\" 36' E"));
	}

}
