package org.polushin.carfactory.engines;

import org.polushin.carfactory.Factory;
import org.polushin.carfactory.ProductionConfig;

import java.util.logging.Logger;

/**
 * Фабрика двигателей.
 */
public class EnginesFactory extends Factory<Engine> {

	static final Logger log = Logger.getLogger("Engines");

	public EnginesFactory(ProductionConfig config) throws RuntimeException {
		super(EnginesFactory.class.getSimpleName(), 1, new EnginesStock(config.enginesStockSize));

		try {
			pool.runTask(new EnginesProvider(stock, config));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
