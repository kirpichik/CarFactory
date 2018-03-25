package org.polushin.carfactory.accessories;

import org.polushin.carfactory.Factory;
import org.polushin.carfactory.ProductionConfig;
import org.polushin.carfactory.ProductionProvider;

import java.util.logging.Logger;

/**
 * Фабрика производства аксессуаров для автомобилей.
 */
public class AccessoriesFactory extends Factory<Accessory> {

	static final Logger log = Logger.getLogger("Accessories");

	private final ProductionProvider[] providers;

	public AccessoriesFactory(ProductionConfig config) throws RuntimeException {
		super(AccessoriesFactory.class.getSimpleName(), config.accessoriesProviders,
		      new AccessoriesStock(config.accessoriesStockSize));

		providers = new ProductionProvider[config.accessoriesProviders];

		for (int i = 0; i < config.accessoriesProviders; i++) {
			try {
				pool.runTask(providers[i] = new AccessoriesProvider(stock, config));
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
}
