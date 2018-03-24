package org.polushin.carfactory.engines;

import org.polushin.carfactory.AbstractStock;

/**
 * Склад двигателей.
 */
public class EnginesStock extends AbstractStock<Engine> {

	public EnginesStock(int maxSize) {
		super(maxSize);
	}

}
