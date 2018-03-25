package org.polushin.carfactory.carcass;

import org.polushin.carfactory.ProductionProvider;
import org.polushin.carfactory.Stock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Производитель кузовов.
 */
public class CarcassesProvider implements ProductionProvider<Carcass> {

	private final Stock<Carcass> stock;
	private final AtomicInteger count = new AtomicInteger();

	public CarcassesProvider(Stock<Carcass> stock) {
		this.stock = stock;
	}

	@Override
	public void run() {
		CarcassesFactory.log.fine("Carcasses provider started.");
		try {
			while (true) {
				Carcass carcass = new Carcass();
				CarcassesFactory.log.fine("Created: " + carcass);
				CarcassesFactory.log.fine("Storage current size: " + stock.getSize() + "/" + stock.getMaxSize());
				stock.addProduction(carcass);
				CarcassesFactory.log.fine("Stored: " + carcass);
				count.incrementAndGet();
			}
		} catch (InterruptedException ignored) {}
		CarcassesFactory.log.fine("Carcasses provider exited.");
	}

	@Override
	public int getCount() {
		return count.get();
	}
}
