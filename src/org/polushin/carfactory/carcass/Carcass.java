package org.polushin.carfactory.carcass;

import org.polushin.carfactory.Product;

import java.util.concurrent.TimeUnit;

/**
 * Кузов.
 */
public class Carcass extends Product {

	public static final long BUILD_DELAY = 1000;

	public Carcass() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(BUILD_DELAY);
	}

	@Override
	public String toString() {
		return "I'm Carcass with UUID: " + getUUID();
	}
}
