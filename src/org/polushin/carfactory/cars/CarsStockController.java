package org.polushin.carfactory.cars;

import org.polushin.carfactory.Stock;
import org.polushin.carfactory.StockUpdateListener;

/**
 * Контроллер склада автомобилей.
 */
public class CarsStockController extends Thread implements StockUpdateListener<Car> {

	private final CarsFactory factory;
	private volatile boolean update;

	public CarsStockController(CarsFactory factory) {
		this.factory = factory;
		setName("Stock contoller");
		start();
	}

	@Override
	public void onUpdate(Stock<Car> stock) {
		synchronized (factory) {
			update = true;
			factory.notify();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {

				for (int i = 0; i < factory.getWorkersMaxCount() && i < factory.getStock().freeSpace(); i++)
					factory.tryToWakeupWorker();

				if (!update)
					synchronized (factory) {
						factory.wait();
					}
				update = false;
			}
		} catch (InterruptedException ignored) {
		}
	}
}
