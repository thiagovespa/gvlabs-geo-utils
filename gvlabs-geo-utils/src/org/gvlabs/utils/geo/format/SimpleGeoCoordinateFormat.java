package org.gvlabs.utils.geo.format;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

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
			seconds = (int) Math.round((minutesD - minutes)
					* SIXTH);
			// round adjustments
			if (seconds >= 60) {
				seconds -= 60;
				minutes++;
			}
			if (minutes >= 60) {
				minutes -= 60;
				degree++;
			}

			toAppendTo.append(degree).append("\u00BA ").append(minutes)
					.append("\" ").append(seconds).append("\' ");
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
		double degreeValue = 0.0;

		return degreeValue;
	}

}
