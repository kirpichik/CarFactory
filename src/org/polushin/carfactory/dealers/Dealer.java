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
		try {
			while (true) {
				cash.getAndAdd(new Money(carStock.getProduction()).getAmount());
			}
		} catch (InterruptedException ignored) {}
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
