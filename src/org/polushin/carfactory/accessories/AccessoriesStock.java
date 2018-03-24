package org.polushin.carfactory.accessories;

import org.polushin.carfactory.AbstractStock;

/**
 * Склад аксессуаров.
 */
public class AccessoriesStock extends AbstractStock<Accessory> {

	public AccessoriesStock(int maxSize) {
		super(maxSize);
	}
}
