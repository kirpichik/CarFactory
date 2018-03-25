package org.polushin.carfactory.accessories;

import org.polushin.carfactory.Product;

import java.util.concurrent.TimeUnit;

/**
 * Аксессуар.
 */
public class Accessory extends Product {

	public static final long BUILD_DELAY = 1000;

	public Accessory() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(BUILD_DELAY);
	}

	@Override
	public String toString() {
		return "I'm Accessory with UUID: " + getUUID();
	}
}
