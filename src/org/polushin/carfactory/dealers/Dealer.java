package org.polushin.carfactory.dealers;

import org.polushin.carfactory.ProductionProvider;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Продавец автомобилей.
 */
public class Dealer implements ProductionProvider {

	public static final int CAR_PRICE = 999;
	public static final long CAR_SALE_DELAY = 10000;

	private final AtomicInteger cash = new AtomicInteger();

	@Override
	public void run() {
		try {
			while (true)
				cash.getAndAdd(new Money(CAR_PRICE, CAR_SALE_DELAY).getAmount());
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
		return getCash() / CAR_PRICE;
	}
}
