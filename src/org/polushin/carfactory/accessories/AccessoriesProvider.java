package org.polushin.carfactory.accessories;

import org.polushin.carfactory.ProductionProvider;
import org.polushin.carfactory.Stock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Производитель аксессуаров.
 */
public class AccessoriesProvider implements ProductionProvider<Accessory> {

	private final Stock<Accessory> stock;
	private final AtomicInteger count = new AtomicInteger();

	public AccessoriesProvider(Stock<Accessory> stock) {
		this.stock = stock;
	}

	@Override
	public void run() {
		try {
			while (true) {
				stock.addProduction(new Accessory());
				count.incrementAndGet();
			}
		} catch (InterruptedException ignored) {}
	}

	@Override
	public int getCount() {
		return count.get();
	}
}
