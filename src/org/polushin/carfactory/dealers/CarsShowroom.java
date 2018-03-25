package org.polushin.carfactory.dealers;

import org.polushin.carfactory.Factory;
import org.polushin.carfactory.ProductionConfig;
import org.polushin.carfactory.Stock;
import org.polushin.carfactory.cars.Car;

import java.util.logging.Logger;

/**
 * Автосалон.
 */
public class CarsShowroom extends Factory<Money> {

	static final Logger log = Logger.getLogger("Showroom");

	public CarsShowroom(Stock<Car> stock, ProductionConfig config) throws RuntimeException {
		super("Car showroom", config.dealersCount, null);
		for (int i = 0; i < config.dealersCount; i++) {
			try {
				pool.runTask(new Dealer(stock, config));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
