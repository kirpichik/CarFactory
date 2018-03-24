package org.polushin.carfactory.dealers;

import org.polushin.carfactory.Product;
import org.polushin.carfactory.cars.Car;

import java.util.concurrent.TimeUnit;

/**
 * Деньги.
 */
public class Money extends Product {

	public static final int CAR_PRICE = 999;
	public static final long CAR_SALE_DELAY = 10000;

	private final int amount;

	public Money(Car car) throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(CAR_SALE_DELAY);
		this.amount = CAR_PRICE;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "I'm money! Amount: " + amount;
	}
}
