package org.polushin.carfactory;

/**
 * Склад продукции, контролируемый обработчиком обновлений.
 *
 * @param <Prod> Тип продукции на складе.
 */
public interface ControlledStock<Prod extends Product> extends Stock<Prod> {

	/**
	 * Устанавливает обработчик события обновления продукции на складе.
	 *
	 * @param listener Слушатель событий.
	 */
	void setUpdateListener(StockUpdateListener<Prod> listener);

}
