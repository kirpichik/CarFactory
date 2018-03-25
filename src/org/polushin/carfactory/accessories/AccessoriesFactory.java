package org.polushin.carfactory.accessories;

import org.polushin.carfactory.Factory;
import org.polushin.carfactory.ProductionConfig;

import java.util.logging.Logger;

/**
 * Фабрика производства аксессуаров для автомобилей.
 */
public class AccessoriesFactory extends Factory<Accessory> {

	static final Logger log = Logger.getLogger("Accessories");

	public AccessoriesFactory(ProductionConfig config) throws RuntimeException {
		super(AccessoriesFactory.class.getSimpleName(), config.accessoriesProviders,
		      new AccessoriesStock(config.accessoriesStockSize));

		for (int i = 0; i < config.accessoriesProviders; i++) {
			try {
				pool.runTask(new AccessoriesProvider(stock, config));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
