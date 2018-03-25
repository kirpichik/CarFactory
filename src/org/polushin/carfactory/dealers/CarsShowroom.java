package org.polushin.carfactory.dealers;

import org.polushin.carfactory.Factory;
import org.polushin.carfactory.ProductionConfig;
import org.polushin.carfactory.ProductionProvider;
import org.polushin.carfactory.Stock;
import org.polushin.carfactory.cars.Car;

import java.util.logging.Logger;

/**
 * Автосалон.
 */
public class CarsShowroom extends Factory<Money> {

	static final Logger log = Logger.getLogger("Showroom");

	private final Dealer[] providers;

	public CarsShowroom(Stock<Car> stock, ProductionConfig config) throws RuntimeException {
		super("Car showroom", config.dealersCount, null);

		providers = new Dealer[config.dealersCount];

		for (int i = 0; i < config.dealersCount; i++) {
			try {
				pool.runTask(providers[i] = new Dealer(stock, config));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public int getCount() {
		int sum = 0;
		for (ProductionProvider provider : providers)
			sum += provider.getCount();
		return sum;
	}

	public int getCash() {
		int sum = 0;
		for (Dealer provider : providers)
			sum += provider.getCash();
		return sum;
	}
}
