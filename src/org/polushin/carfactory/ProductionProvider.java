package org.polushin.carfactory;

/**
 * Производитель продукии.
 *
 * @param <Prod> Тип продукции.
 */
public interface ProductionProvider<Prod extends Product> extends Runnable {

	/**
	 * @return Кол-во произведенной продукции.
	 */
	int getCount();

}
