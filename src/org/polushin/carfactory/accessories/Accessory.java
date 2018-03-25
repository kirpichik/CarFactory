package org.polushin.carfactory.accessories;

import org.polushin.carfactory.Product;

import java.util.concurrent.TimeUnit;

/**
 * Аксессуар.
 */
public class Accessory extends Product {

	public Accessory(int delay) throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(delay);
	}

	@Override
	public String toString() {
		return "I'm Accessory with UUID: " + getUUID();
	}
}
