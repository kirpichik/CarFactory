package org.polushin.carfactory.accessories;

import org.polushin.carfactory.Factory;

import java.util.logging.Logger;

/**
 * Фабрика производства аксессуаров для автомобилей.
 */
public class AccessoriesFactory extends Factory<Accessory> {

	static final Logger log = Logger.getLogger("Accessories");

	/**
	 * @param stockMaxSize Максимальный размер склада.
	 * @param providers Кол-во поставщиков аксессуаров.
	 *
	 * @throws RuntimeException Ошибка при создании фабрики.
	 */
	public AccessoriesFactory(int stockMaxSize, int providers) throws RuntimeException {
		super(AccessoriesFactory.class.getSimpleName(), providers, new AccessoriesStock(stockMaxSize));

		for (int i = 0; i < providers; i++) {
			try {
				pool.runTask(new AccessoriesProvider(stock));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
