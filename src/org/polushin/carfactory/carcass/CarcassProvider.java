package org.polushin.carfactory.carcass;

import org.polushin.carfactory.ProductionProvider;
import org.polushin.carfactory.Stock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Производитель кузовов.
 */
public class CarcassProvider implements ProductionProvider<Carcass> {

	private final Stock<Carcass> stock;
	private final AtomicInteger count = new AtomicInteger();

	public CarcassProvider(Stock<Carcass> stock) {
		this.stock = stock;
	}

	@Override
	public void run() {
		try {
			while (true) {
				stock.addProduction(new Carcass());
				count.incrementAndGet();
			}
		} catch (InterruptedException ignored) {}
	}

	@Override
	public int getCount() {
		return count.get();
	}
}
