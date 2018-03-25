package org.polushin.carfactory;

import org.polushin.threadpoll.ThreadPool;

/**
 * Фабрика по производству продукции.
 */
public abstract class Factory<Prod extends Product> {

	protected final ThreadPool pool;
	protected final Stock<Prod> stock;

	/**
	 * @param name Имя фабрики (пула потоков фабрики)
	 * @param size Максимальное кол-во работников на фабрике.
	 */
	public Factory(String name, int size, Stock<Prod> stock) {
		pool = new ThreadPool(name, size);
		this.stock = stock;
	}

	/**
	 * Завершает работу всей фабрики.
	 */
	public void stopFactory() {
		pool.stopAll();
	}

	/**
	 * @return Максимальное кол-во работников на фабрике.
	 */
	public int getWorkersMaxCount() {
		return pool.getPoolSize();
	}

	/**
	 * @return Склад продукции данной фабрики.
	 */
	public Stock<Prod> getStock() {
		return stock;
	}

	/**
	 * @return Кол-во произведенной продукции.
	 */
	public abstract int getCount();

}
