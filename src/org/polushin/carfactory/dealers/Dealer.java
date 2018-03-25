package org.polushin.carfactory.dealers;

import org.polushin.carfactory.ProductionProvider;
import org.polushin.carfactory.Stock;
import org.polushin.carfactory.cars.Car;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Продавец автомобилей.
 */
public class Dealer implements ProductionProvider {

	private final Stock<Car> carStock;

	private final AtomicInteger cash = new AtomicInteger();

	public Dealer(Stock<Car> cars) {
		carStock = cars;
	}

	@Override
	public void run() {
		CarsShowroom.log.fine("Dealer started.");
		try {
			while (true) {
				CarsShowroom.log.fine("Waiting car from stock...");
				Car car = carStock.getProduction();
				CarsShowroom.log.fine("Car put from stock: " + car);
				Money money = new Money(car);
				cash.getAndAdd(money.getAmount());
				CarsShowroom.log.info("Car sold. Current dealer cash: " + cash.get());
			}
		} catch (InterruptedException ignored) {}
		CarsShowroom.log.fine("Dealer exited.");
	}

	/**
	 * @return Заработанная сумма продаж данного диллера.
	 */
	public int getCash() {
		return cash.get();
	}

	@Override
	public int getCount() {
		return getCash() / Money.CAR_PRICE;
	}
}
