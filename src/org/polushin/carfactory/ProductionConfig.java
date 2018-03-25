package org.polushin.carfactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;

/**
 * Конфигурация производства.
 */
public class ProductionConfig {

	private static final Gson GSON;

	static {
		GsonBuilder builder = new GsonBuilder();
		JsonDeserializer<ProductionConfig> des = (json, t, c) -> new ProductionConfig(json.getAsJsonObject());
		builder.registerTypeAdapter(ProductionConfig.class, des);
		GSON = builder.create();
	}

	public final int enginesStockSize;
	public volatile int enginesDelay;

	public final int carcassesStockSize;
	public volatile int carcassesDelay;

	public final int accessoriesStockSize;
	public volatile int accessoriesDelay;
	public final int accessoriesProviders;

	public final int carsStockSize;
	public volatile int carsDelay;
	public final int carsWorkers;

	public volatile int saleDelay;
	public final int pricePerSale;
	public final int dealersCount;

	public final boolean logSales;

	private ProductionConfig(JsonObject o) {
		JsonObject block = o.getAsJsonObject("engines");
		enginesStockSize = block.get("stock_size").getAsInt();
		enginesDelay = block.get("delay").getAsInt();

		block = o.getAsJsonObject("carcasses");
		carcassesStockSize = block.get("stock_size").getAsInt();
		carcassesDelay = block.get("delay").getAsInt();

		block = o.getAsJsonObject("accessories");
		accessoriesStockSize = block.get("stock_size").getAsInt();
		accessoriesDelay = block.get("delay").getAsInt();
		accessoriesProviders = block.get("providers").getAsInt();

		block = o.getAsJsonObject("cars");
		carsStockSize = block.get("stock_size").getAsInt();
		carsDelay = block.get("delay").getAsInt();
		carsWorkers = block.get("workers").getAsInt();

		block = o.getAsJsonObject("showroom");
		saleDelay = block.get("delay").getAsInt();
		pricePerSale = block.get("car_price").getAsInt();
		dealersCount = block.get("dealers").getAsInt();

		logSales = o.get("log_sales").getAsBoolean();
	}

	public static ProductionConfig fromReader(Reader reader) {
		return GSON.fromJson(reader, new TypeToken<ProductionConfig>() {}.getType());
	}
}
