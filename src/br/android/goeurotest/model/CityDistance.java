package br.android.goeurotest.model;

/**
 * City class.
 * 
 * @author Rodrigo Cericatto
 * @since Oct 11 2013
 */
public class CityDistance {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	
	private City city;
	
	private Double distance;
	
	//--------------------------------------------------
	// Constructor
	//--------------------------------------------------
	
	public CityDistance() {}

	public CityDistance(City city, Double distance) {
		this.city = city;
		this.distance = distance;
	}

	//--------------------------------------------------
	// To String
	//--------------------------------------------------
	
	@Override
	public String toString() {
		return "CityDistance [city=" + city + ", distance=" + distance + "]";
	}
	
	//--------------------------------------------------
	// Comparator
	//--------------------------------------------------
	
	public int compareTo(Double otherDistance) {
		if (this.distance < otherDistance) {
			return -1;
		}
		if (this.distance > otherDistance) {
			return 1;
		}
		return 0;
	}

	//--------------------------------------------------
	// Getters and Setters
	//--------------------------------------------------
	
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}

	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}	
}