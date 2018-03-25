package org.polushin.carfactory.dealers;

import org.polushin.carfactory.Product;
import org.polushin.carfactory.cars.Car;

import java.util.concurrent.TimeUnit;

/**
 * Деньги.
 */
public class Money extends Product {

	private final int amount;

	public Money(Car car, int delay, int price) throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(delay);
		this.amount = price;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "I'm money! Amount: " + amount;
	}
}
