package org.gvlabs.utils.geo.format;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import org.gvlabs.utils.geo.GeoType;


/**
 * A class used to format a coordinate. Eg. 1.5 to 1&ordm; 30" 00' N or vice-versa 
 * 
 * @author Thiago Galbiatti Vespa
 * @version 1.0
 */
public class SimpleGeoCoordinateFormat extends Format {

	/**
	 * 
	 */
	private static final long serialVersionUID = 201210161409L;
	
	private GeoType geoType;

	public SimpleGeoCoordinateFormat(GeoType geoType) {
		this.geoType = geoType;
	}

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
		int degree = 0;
		int minutes = 0;
		int seconds = 0;
		if (obj instanceof Number) {
			double value = ((Number) obj).doubleValue();
			degree = (int) value;
			minutes = (int) (value - degree * 60.0);
			seconds = (int) (value - (60.0 * (degree * 60 + minutes)));
			toAppendTo.append(degree).append("\u00BA ").append(minutes)
					.append("\" ").append(seconds).append("\' ");
			if(GeoType.LONGITUDE.equals(geoType)) {
				if(value<0) {
					toAppendTo.append("S");
				} else {
					toAppendTo.append("N");
				}
			} else {
				if(value<0) {
					toAppendTo.append("W");
				} else {
					toAppendTo.append("E");
				}
			}

		}
		return toAppendTo;
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		double degreeValue = 0.0;
		
		return degreeValue;
	}

}
