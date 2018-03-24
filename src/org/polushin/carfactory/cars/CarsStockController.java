package org.polushin.carfactory.cars;

import org.polushin.carfactory.Stock;
import org.polushin.carfactory.StockUpdateListener;

/**
 * Контроллер склада автомобилей.
 */
public class CarsStockController implements StockUpdateListener<Car> {

	private final CarsFactory factory;

	public CarsStockController(CarsFactory factory) {
		this.factory = factory;
	}

	@Override
	public void onUpdate(Stock<Car> stock) {
		int free = stock.freeSpace();
		for (int i = 0; i < factory.getWorkersMaxCount() && i < free; i++)
			factory.tryToWakeupWorker();
	}
}
