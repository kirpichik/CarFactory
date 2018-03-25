package org.polushin.carfactory.carcass;

import org.polushin.carfactory.Factory;
import org.polushin.carfactory.ProductionConfig;

import java.util.logging.Logger;

/**
 * Фабрика кузовов.
 */
public class CarcassesFactory extends Factory<Carcass> {

	static final Logger log = Logger.getLogger("Carcasses");

	public CarcassesFactory(ProductionConfig config) throws RuntimeException {
		super(CarcassesFactory.class.getSimpleName(), 1, new CarcassesStock(config.carcassesStockSize));

		try {
			pool.runTask(new CarcassesProvider(stock, config));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
