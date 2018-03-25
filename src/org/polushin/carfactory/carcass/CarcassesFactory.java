package org.polushin.carfactory.carcass;

import org.polushin.carfactory.Factory;

import java.util.logging.Logger;

/**
 * Фабрика кузовов.
 */
public class CarcassesFactory extends Factory<Carcass> {

	static final Logger log = Logger.getLogger("Carcasses");

	/**
	 * @param stockMaxSize Максимальный размер склада.
	 * @param providers Кол-во поставщиков кузовов.
	 *
	 * @throws RuntimeException Ошибка при создании фабрики.
	 */
	public CarcassesFactory(int stockMaxSize, int providers) throws RuntimeException {
		super(CarcassesFactory.class.getSimpleName(), providers, new CarcassesStock(stockMaxSize));

		for (int i = 0; i < providers; i++) {
			try {
				pool.runTask(new CarcassesProvider(stock));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
