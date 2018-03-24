package org.polushin.carfactory.dealers;

import org.polushin.carfactory.Factory;
import org.polushin.carfactory.Stock;
import org.polushin.carfactory.cars.Car;

/**
 * Автосалон.
 */
public class CarsShowroom extends Factory<Money> {

	/**
	 * @param stock Склад автомобилей на продажу.
	 * @param dealers Кол-во продавцов.
	 *
	 * @throws RuntimeException Ошибка создания автосалона.
	 */
	public CarsShowroom(Stock<Car> stock, int dealers) throws RuntimeException {
		super("Car showroom", dealers, null);
		for (int i = 0; i < dealers; i++) {
			try {
				pool.runTask(new Dealer(stock));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
