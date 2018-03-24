package org.polushin.carfactory.cars;

import org.polushin.carfactory.AbstractStock;

/**
 * Склад автомобилей.
 */
public class CarsStock extends AbstractStock<Car> {

	public CarsStock(int maxSize) {
		super(maxSize);
	}
}
