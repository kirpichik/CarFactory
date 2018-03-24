package org.polushin.carfactory;

import java.util.UUID;

/**
 * Единица продукции с уникальным идентификатором.
 */
public abstract class Product {

	private final UUID uuid = UUID.randomUUID();

	/**
	 * @return Уникальный идентификатор продукта.
	 */
	public final UUID getUUID() {
		return uuid;
	}

	@Override
	public abstract String toString();
}
