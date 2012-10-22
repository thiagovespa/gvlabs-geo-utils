package org.gvlabs.utils.geo.format;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Scanner;
import java.util.regex.MatchResult;

import org.gvlabs.utils.geo.GeoType;

/**
 * A class used to format a coordinate. Eg. 1.5 to 1&ordm; 30" 00' N or
 * vice-versa
 * 
 * @author Thiago Galbiatti Vespa
 * @version 1.0
 */
public class SimpleGeoCoordinateFormat extends Format {

	/**
	 * 
	 */
	private static final long serialVersionUID = 201210161409L;

	private static final double SIXTH = 60.0;
	private static final int PRECISION = 20;

	private GeoType geoType;

	/**
	 * Formatter constructor
	 * 
	 * @param geoType
	 *            latitude or longitude
	 */
	public SimpleGeoCoordinateFormat(GeoType geoType) {
		this.geoType = geoType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.text.Format#format(java.lang.Object, java.lang.StringBuffer,
	 * java.text.FieldPosition)
	 */
	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
		int degree = 0;
		int minutes = 0;
		int seconds = 0;
		boolean isNegative = false;
		if (obj instanceof Number) {
			double value = ((Number) obj).doubleValue();
			if (value < 0) {
				isNegative = true;
			}
			value = Math.abs(value);
			degree = (int) value;
			double minutesD = (value - degree) * SIXTH;
			minutes = (int) minutesD;
			seconds = (int) Math.round((minutesD - minutes) * SIXTH);
			// round adjustments
			if (seconds >= SIXTH) {
				seconds -= SIXTH;
				minutes++;
			}
			if (minutes >= SIXTH) {
				minutes -= SIXTH;
				degree++;
			}

			toAppendTo.append(degree).append("\u00BA ").append(minutes)
					.append("\" ").append(seconds).append("' ");
			if (GeoType.LATITUDE.equals(geoType)) {
				if (isNegative) {
					toAppendTo.append("S");
				} else {
					toAppendTo.append("N");
				}
			} else {
				if (isNegative) {
					toAppendTo.append("W");
				} else {
					toAppendTo.append("E");
				}
			}

		}
		return toAppendTo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.text.Format#parseObject(java.lang.String,
	 * java.text.ParsePosition)
	 */
	@Override
	public Object parseObject(String source, ParsePosition pos) {
		BigDecimal degreeValue = new BigDecimal("0");
		Scanner s = new Scanner(source);
		s.findInLine("(\\d+)\u00BA (\\d+)\" (\\d+)' ([N|S|E|W])");

		MatchResult result = s.match();
		BigDecimal factor = new BigDecimal(SIXTH);
		
		for (int i = 1; i < result.groupCount(); i++) {
			degreeValue = degreeValue.add(new BigDecimal(result.group(i))
					.divide(factor.pow(i-1), PRECISION, RoundingMode.HALF_EVEN));
		}
		s.close();

		String position = result.group(result.groupCount());
		if ("S".equals(position) || "W".equals(position)) {
			degreeValue = degreeValue.negate();
		}
		pos.setIndex(source.length());
		return degreeValue.doubleValue();
	}

	/**
	 * Parse a latitude or longitude to a Integer
	 * 
	 * @param source
	 *            string to parse
	 * @return parsed latitude or longitude
	 * @throws ParseException
	 *             when
	 */
	public Integer parse(String source) throws ParseException {
		return (Integer) this.parseObject(source);
	}

}
