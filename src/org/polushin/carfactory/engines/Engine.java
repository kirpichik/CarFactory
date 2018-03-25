package org.polushin.carfactory.engines;

import org.polushin.carfactory.Product;

import java.util.concurrent.TimeUnit;

/**
 * Двигатель.
 */
public class Engine extends Product {

	public Engine(int delay) throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(delay);
	}

	@Override
	public String toString() {
		return "I'm Engine with UUID: " + getUUID();
	}
}
