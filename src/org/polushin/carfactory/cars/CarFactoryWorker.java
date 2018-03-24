package org.polushin.carfactory.cars;

import org.polushin.carfactory.ProductionProvider;
import org.polushin.carfactory.Stock;
import org.polushin.carfactory.accessories.Accessory;
import org.polushin.carfactory.carcass.Carcass;
import org.polushin.carfactory.engines.Engine;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Производитель автомобилей.
 */
public class CarFactoryWorker implements ProductionProvider<Car> {

	private final Stock<Car> stock;
	private final Stock<Accessory> accessoriesStock;
	private final Stock<Engine> enginesStock;
	private final Stock<Carcass> carcassesStock;
	private final AtomicInteger count = new AtomicInteger();

	public CarFactoryWorker(Stock<Accessory> acs, Stock<Engine> engines, Stock<Carcass> carcasses, Stock<Car> stock) {
		this.stock = stock;
		accessoriesStock = acs;
		enginesStock = engines;
		carcassesStock = carcasses;
	}

	@Override
	public void run() {
		try {
			Accessory accessory = accessoriesStock.getProduction();
			Engine engine = enginesStock.getProduction();
			Carcass carcass = carcassesStock.getProduction();
			stock.addProduction(new Car(accessory, engine, carcass));
			count.incrementAndGet();
		} catch (InterruptedException ignored) {
		}
	}

	@Override
	public int getCount() {
		return count.get();
	}
}
