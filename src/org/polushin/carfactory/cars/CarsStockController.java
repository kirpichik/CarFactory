package org.polushin.carfactory.cars;

import org.polushin.carfactory.Stock;
import org.polushin.carfactory.StockUpdateListener;

/**
 * Контроллер склада автомобилей.
 */
public class CarsStockController extends Thread implements StockUpdateListener<Car> {

	private final CarsFactory factory;

	public CarsStockController(CarsFactory factory) {
		this.factory = factory;
		start();
	}

	@Override
	public void onUpdate(Stock<Car> stock) {
		synchronized (factory) {
			factory.notify();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {

				int free = factory.getStock().freeSpace();
				for (int i = 0; i < factory.getWorkersMaxCount() && i < free; i++)
					factory.tryToWakeupWorker();

				synchronized (factory) {
					factory.wait();
				}
			}
		} catch (InterruptedException ignored) {
		}
	}
}
