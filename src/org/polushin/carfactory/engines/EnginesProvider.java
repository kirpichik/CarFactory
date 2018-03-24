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
		try {
			while (true) {
				stock.addProduction(new Engine());
				count.incrementAndGet();
			}
		} catch (InterruptedException ignored) {}
	}

	@Override
	public int getCount() {
		return count.get();
	}
}
