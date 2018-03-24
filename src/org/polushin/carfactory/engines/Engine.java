package org.polushin.carfactory.engines;

import org.polushin.carfactory.Product;

import java.util.concurrent.TimeUnit;

/**
 * Двигатель.
 */
public class Engine extends Product {

	public static final long BUILD_DELAY = 2000;

	public Engine() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(BUILD_DELAY);
	}

	@Override
	public String toString() {
		return "I'm Engine with UUID: " + getUUID();
	}
}
