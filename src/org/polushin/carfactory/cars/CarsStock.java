package org.polushin.carfactory.cars;

import org.polushin.carfactory.AbstractStock;
import org.polushin.carfactory.ControlledStock;
import org.polushin.carfactory.StockUpdateListener;

/**
 * Склад автомобилей.
 */
public class CarsStock extends AbstractStock<Car> implements ControlledStock<Car> {

	private volatile StockUpdateListener<Car> listener;

	public CarsStock(int maxSize) {
		super(maxSize);
	}

	@Override
	public void setUpdateListener(StockUpdateListener<Car> listener) {
		this.listener = listener;
	}

	@Override
	public Car getProduction() throws InterruptedException {
		Car car = super.getProduction();
		listener.onUpdate(this);
		return car;
	}

	@Override
	public void addProduction(Car production) throws InterruptedException {
		super.addProduction(production);
		listener.onUpdate(this);
	}

	@Override
	public void setMaxSize(int maxSize) {
		super.setMaxSize(maxSize);
		listener.onUpdate(this);
	}
}
