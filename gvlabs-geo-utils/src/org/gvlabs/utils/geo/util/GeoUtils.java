package org.gvlabs.utils.geo.util;


/**
 * Geo-coordinate operations
 * 
 * @author Thiago Galbiatti Vespa
 * @version 1.1
 */
public class GeoUtils {

	/**
	 * Earth radius
	 * http://en.wikipedia.org/wiki/Earth_radius
	 */
	public static int EARTH_RADIUS_KM = 6371; //*1000

	/**
	 * Distance between two geo coordinates
	 * 
	 * @param firstLatitude
	 *            First coordinate latitude
	 * @param firstLongitude
	 *            First coordinate longitude
	 * @param secondLatitude
	 *            Second coordinate latitude
	 * @param secondLongitude
	 *            Second coordinate longitude
	 * 
	 * @return Distance between two coordinates
	 */
	public static double geoDistanceInKm(double firstLatitude,
			double firstLongitude, double secondLatitude, double secondLongitude) {

		double firstLatToRad = Math.toRadians(firstLatitude);
		double secondLatToRad = Math.toRadians(secondLatitude);

		double deltaLongitudeInRad = Math.toRadians(secondLongitude
				- firstLongitude);

		return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
				* Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
				* Math.sin(secondLatToRad))
				* EARTH_RADIUS_KM;
	}

}
