package org.polushin.carfactory.engines;

import org.polushin.carfactory.Factory;

import java.util.logging.Logger;

/**
 * Фабрика двигателей.
 */
public class EnginesFactory extends Factory<Engine> {

	static final Logger log = Logger.getLogger("Engines");

	/**
	 * @param stockMaxSize Максимальный размер склада.
	 * @param providers Кол-во поставщиков двигателей.
	 *
	 * @throws RuntimeException Ошибка при создании фабрики.
	 */
	public EnginesFactory(int stockMaxSize, int providers) throws RuntimeException {
		super(EnginesFactory.class.getSimpleName(), providers, new EnginesStock(stockMaxSize));

		for (int i = 0; i < providers; i++) {
			try {
				pool.runTask(new EnginesProvider(stock));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
