package org.polushin.carfactory;

/**
 * Склад произведенной продукции.
 *
 * @param <Prod> Тип хранимой продукции.
 */
public interface Stock<Prod extends Product> {

	/**
	 * Поставляет продукцию на склад.
	 * Если на складе нет места, блокируется до появления места.
	 *
	 * @param production Поставляемая продукция.
	 *
	 * @throws InterruptedException Ожидание было прервано.
	 */
	void addProduction(Prod production) throws InterruptedException;

	/**
	 * @return Кол-во продукции на складе.
	 */
	int getSize();

	/**
	 * Устанавливает новый размер склада.
	 *
	 * @param maxSize Новый размер.
	 */
	void setMaxSize(int maxSize);

	/**
	 * Забирает продукцию со склада.
	 * Если на складе нет продукции, ожидает до ее появления.
	 *
	 * @return Единица продукции.
	 *
	 * @throws InterruptedException Ожидание было прервано.
	 */
	Prod getProduction() throws InterruptedException;

}
