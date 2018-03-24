package org.polushin.carfactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Склад произведенной продукции.
 *
 * @param <Prod> Тип хранимой продукции.
 */
public class AbstractStock<Prod extends Product> implements Stock<Prod> {

	protected final List<Prod> products;
	protected int maxSize;

	/**
	 * @param maxSize Максимальный размер склада.
	 */
	public AbstractStock(int maxSize) {
		products = new ArrayList<>(maxSize);
		this.maxSize = maxSize;
	}

	@Override
	public void addProduction(Prod production) throws InterruptedException {
		synchronized (products) {
			while (products.size() == maxSize)
				products.wait();
			products.add(production);
			products.notifyAll();
		}
	}

	@Override
	public int getSize() {
		synchronized (products) {
			return products.size();
		}
	}

	@Override
	public void setMaxSize(int maxSize) {
		synchronized (products) {
			int remove = products.size() - maxSize;
			if (remove > 0)
				for (int i = 0; i < remove; i++)
					products.remove(products.size() - 1);
			this.maxSize = maxSize;
			products.notifyAll();
		}
	}

	@Override
	public Prod getProduction() throws InterruptedException {
		Prod engine;
		synchronized (products) {
			while (products.isEmpty())
				products.wait();
			engine = products.remove(products.size() - 1);
			products.notifyAll();
		}
		return engine;
	}
}
