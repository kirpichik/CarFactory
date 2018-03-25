package org.polushin.carfactory.carcass;

import org.polushin.carfactory.Product;

import java.util.concurrent.TimeUnit;

/**
 * Кузов.
 */
public class Carcass extends Product {

	public Carcass(int delay) throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(delay);
	}

	@Override
	public String toString() {
		return "I'm Carcass with UUID: " + getUUID();
	}
}
