package org.polushin.carfactory.carcass;

import org.polushin.carfactory.AbstractStock;

/**
 * Склад кузовов.
 */
public class CarcassStock extends AbstractStock<Carcass> {

	public CarcassStock(int maxSize) {
		super(maxSize);
	}
}
