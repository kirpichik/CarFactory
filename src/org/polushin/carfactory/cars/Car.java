package org.polushin.carfactory.cars;

import org.polushin.carfactory.Product;
import org.polushin.carfactory.accessories.Accessory;
import org.polushin.carfactory.carcass.Carcass;
import org.polushin.carfactory.engines.Engine;

import java.util.concurrent.TimeUnit;

/**
 * Автомобиль.
 */
public class Car extends Product {

	public static final long BUILD_DELAY = 5000;

	private final Accessory accessory;
	private final Engine engine;
	private final Carcass carcass;

	public Car(Accessory accessory, Engine engine, Carcass carcass) throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(BUILD_DELAY);
		this.accessory = accessory;
		this.engine = engine;
		this.carcass = carcass;
	}

	@Override
	public String toString() {
		return "I'm car with UUID: " + getUUID() + ". I'm built from: " + accessory + ", " + engine + ", " + carcass;
	}
}
