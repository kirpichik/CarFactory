package org.polushin.carfactory.engines;

import org.polushin.carfactory.ProductionProvider;
import org.polushin.carfactory.Stock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Производитель двигателей.
 */
public class EnginesProvider implements ProductionProvider<Engine> {

	private final Stock<Engine> stock;
	private final AtomicInteger count = new AtomicInteger();

	public EnginesProvider(Stock<Engine> stock) {
		this.stock = stock;
	}

	@Override
	public void run() {
		EnginesFactory.log.fine("Engines provider started.");
		try {
			while (true) {
				Engine engine = new Engine();
				EnginesFactory.log.fine("Created: " + engine);
				EnginesFactory.log.fine("Storage current size: " + stock.getSize() + "/" + stock.getMaxSize());
				stock.addProduction(engine);
				EnginesFactory.log.fine("Stored: " + engine);
				count.incrementAndGet();
			}
		} catch (InterruptedException ignored) {}
		EnginesFactory.log.fine("Engines provider exited.");
	}

	@Override
	public int getCount() {
		return count.get();
	}
}
