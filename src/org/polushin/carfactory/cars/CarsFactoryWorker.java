package org.polushin.carfactory.cars;

import org.polushin.carfactory.ProductionConfig;
import org.polushin.carfactory.ProductionProvider;
import org.polushin.carfactory.Stock;
import org.polushin.carfactory.accessories.Accessory;
import org.polushin.carfactory.carcass.Carcass;
import org.polushin.carfactory.engines.Engine;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Производитель автомобилей.
 */
public class CarsFactoryWorker implements ProductionProvider<Car> {

	private final Stock<Car> stock;
	private final Stock<Accessory> accessoriesStock;
	private final Stock<Engine> enginesStock;
	private final Stock<Carcass> carcassesStock;
	private final ProductionConfig config;

	private final AtomicInteger count = new AtomicInteger();
	private volatile boolean working;

	public CarsFactoryWorker(Stock<Accessory> acs, Stock<Engine> engines, Stock<Carcass> carcasses, Stock<Car> stock,
	                         ProductionConfig config) {
		this.stock = stock;
		this.config = config;
		accessoriesStock = acs;
		enginesStock = engines;
		carcassesStock = carcasses;
	}

	/**
	 * @return Занят ли данный работник сейчас работой.
	 */
	public boolean isWorking() {
		return working;
	}

	@Override
	public void run() {
		CarsFactory.log.fine("Cars factory worker started.");
		try {
			working = true;
			Accessory accessory = accessoriesStock.getProduction();
			Engine engine = enginesStock.getProduction();
			Carcass carcass = carcassesStock.getProduction();
			CarsFactory.log.fine("Car parts collected, building car...");
			Car car = new Car(accessory, engine, carcass, config.carsDelay);
			CarsFactory.log.fine("Created: " + car);
			CarsFactory.log.fine("Storage current size: " + stock.getSize() + "/" + stock.getMaxSize());
			working = false;
			stock.addProduction(car);
			CarsFactory.log.fine("Stored: " + car);
			count.incrementAndGet();
		} catch (InterruptedException ignored) {
		}
		CarsFactory.log.fine("Cars factory worker exited.");
	}

	@Override
	public int getCount() {
		return count.get();
	}
}
