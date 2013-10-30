package br.android.goeurotest.model;

import java.util.Comparator;

public class CityDistanceComparator implements Comparator<CityDistance> {
	public int compare(CityDistance current, CityDistance other) {
		return current.getDistance().compareTo(other.getDistance());
	}
}