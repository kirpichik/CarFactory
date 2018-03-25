package org.polushin.carfactory.cars;

import org.polushin.carfactory.ControlledStock;
import org.polushin.carfactory.Factory;
import org.polushin.carfactory.ProductionConfig;
import org.polushin.carfactory.Stock;
import org.polushin.carfactory.accessories.Accessory;
import org.polushin.carfactory.carcass.Carcass;
import org.polushin.carfactory.engines.Engine;

import java.util.logging.Logger;

/**
 * Фабрика производства автомобилей.
 */
public class CarsFactory extends Factory<Car> {

	static final Logger log = Logger.getLogger("Cars");

	private final ControlledStock<Car> stock;
	private final CarsStockController controller;

	private final CarsFactoryWorker[] workers;

	public CarsFactory(Stock<Engine> engines, Stock<Accessory> accessories, Stock<Carcass> carcasses, ProductionConfig
			config) {
		super("Cars factory", config.carsWorkers, null);

		stock = new CarsStock(config.carsStockSize);

		this.workers = new CarsFactoryWorker[config.carsWorkers];
		for (int i = 0; i < config.carsWorkers; i++)
			this.workers[i] = new CarsFactoryWorker(accessories, engines, carcasses, stock, config);

		controller = new CarsStockController(this);
		stock.setUpdateListener(controller);
		controller.onUpdate(stock);
	}

	/**
	 * Пытается запустить в работу свободного рабочего.
	 */
	void tryToWakeupWorker() throws InterruptedException {
		for (CarsFactoryWorker worker : workers)
			if (!worker.isWorking()) {
				CarsFactory.log.fine("============== Found free worker! =================");
				pool.runTask(worker);
				CarsFactory.log.fine("============== Worker woke up! ====================");
				return;
			}
	}

	@Override
	public Stock<Car> getStock() {
		return stock;
	}

	@Override
	public void stopFactory() {
		controller.interrupt();
		try {
			controller.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.stopFactory();
	}
}
