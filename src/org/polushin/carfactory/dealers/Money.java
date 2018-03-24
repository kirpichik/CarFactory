package org.polushin.carfactory.dealers;

import org.polushin.carfactory.Product;

import java.util.concurrent.TimeUnit;

/**
 * Деньги.
 */
public class Money extends Product {

	private final int amount;

	public Money(int amount, long saleDelay) throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(saleDelay);
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "I'm money! Amount: " + amount;
	}
}
