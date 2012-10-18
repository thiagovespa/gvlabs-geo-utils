package org.gvlabs.utils.geo;

import java.io.Serializable;

import org.gvlabs.utils.geo.util.GeoUtils;

/**
 * GeoCoordinate Bean
 * 
 * @author Thiago Galbiatti Vespa
 * @version 1.2
 * 
 */
public class GeoCoordinate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 201009101110L;
	/**
	 * Latitude in degree
	 */
	private double latitude;
	/**
	 * Longitude in degree
	 */
	private double longitude;

	/**
	 * Constructor
	 * 
	 * @param latitude
	 *            Latitude in degree
	 * @param longitude
	 *            Longitude in degree
	 */
	public GeoCoordinate(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Getter for latitude
	 * 
	 * @return Latitude in degree
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Setter for latitude
	 * 
	 * @param latitude
	 *            Latitude in degree
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Getter for longitude
	 * 
	 * @return Longitude in degree
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Setter longitude
	 * 
	 * @param longitude
	 *            Longitude in degree
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Distance in Km from a geo-coordinate
	 * 
	 * @param coordinate
	 *            coordinate used to calculate the distance 
	 * @return the distance between the two coordinates
	 */
	public double distanceInKm(GeoCoordinate coordinate) {
		return GeoUtils.geoDistanceInKm(this, coordinate);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoCoordinate other = (GeoCoordinate) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

}
