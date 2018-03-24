package org.polushin.carfactory;

/**
 * Обработчик события обновления склада.
 */
public interface StockUpdateListener<Prod extends Product> {

	/**
	 * Событие обновления продукции на складе.
	 *
	 * @param stock Обновившийся склад продукции.
	 */
	void onUpdate(Stock<Prod> stock);

}
