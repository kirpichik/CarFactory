package org.polushin.carfactory.carcass;

import org.polushin.carfactory.AbstractStock;

/**
 * Склад кузовов.
 */
public class CarcassesStock extends AbstractStock<Carcass> {

	public CarcassesStock(int maxSize) {
		super(maxSize);
	}
}
