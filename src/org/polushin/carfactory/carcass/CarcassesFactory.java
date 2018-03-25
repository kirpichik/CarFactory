package org.polushin.carfactory.carcass;

import org.polushin.carfactory.Factory;
import org.polushin.carfactory.ProductionConfig;
import org.polushin.carfactory.ProductionProvider;

import java.util.logging.Logger;

/**
 * Фабрика кузовов.
 */
public class CarcassesFactory extends Factory<Carcass> {

	static final Logger log = Logger.getLogger("Carcasses");

	private final ProductionProvider provider;

	public CarcassesFactory(ProductionConfig config) throws RuntimeException {
		super(CarcassesFactory.class.getSimpleName(), 1, new CarcassesStock(config.carcassesStockSize));

		try {
			pool.runTask(provider = new CarcassesProvider(stock, config));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getCount() {
		return provider.getCount();
	}
}
