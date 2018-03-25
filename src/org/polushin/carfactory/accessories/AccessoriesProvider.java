package org.polushin.carfactory.accessories;

import org.polushin.carfactory.ProductionConfig;
import org.polushin.carfactory.ProductionProvider;
import org.polushin.carfactory.Stock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Производитель аксессуаров.
 */
public class AccessoriesProvider implements ProductionProvider<Accessory> {

	private final Stock<Accessory> stock;
	private final ProductionConfig config;
	private final AtomicInteger count = new AtomicInteger();

	public AccessoriesProvider(Stock<Accessory> stock, ProductionConfig config) {
		this.stock = stock;
		this.config = config;
	}

	@Override
	public void run() {
		AccessoriesFactory.log.fine("Accessories provider started.");
		try {
			while (true) {
				Accessory accessory = new Accessory(config.accessoriesDelay);
				AccessoriesFactory.log.fine("Created: " + accessory);
				AccessoriesFactory.log.fine("Storage current size: " + stock.getSize() + "/" + stock.getMaxSize());
				stock.addProduction(accessory);
				AccessoriesFactory.log.fine("Stored: " + accessory);
				count.incrementAndGet();
			}
		} catch (InterruptedException ignored) {
		}
		AccessoriesFactory.log.fine("Accessories provider exited.");
	}

	@Override
	public int getCount() {
		return count.get();
	}
}
