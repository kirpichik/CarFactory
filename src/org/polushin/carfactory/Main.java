package org.polushin.carfactory;

import org.polushin.carfactory.accessories.AccessoriesFactory;
import org.polushin.carfactory.carcass.CarcassesFactory;
import org.polushin.carfactory.cars.CarsFactory;
import org.polushin.carfactory.dealers.CarsShowroom;
import org.polushin.carfactory.engines.EnginesFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class Main {

	public static void main(String[] args) throws IOException {
		InputStream stream = Main.class.getClassLoader().getResourceAsStream("logging.properties");
		LogManager.getLogManager().readConfiguration(stream);

		ProductionConfig config;
		try (FileReader reader = new FileReader("settings.json")) {
			config = ProductionConfig.fromReader(reader);
		}

		AccessoriesFactory accessories = new AccessoriesFactory(config);
		CarcassesFactory carcasses = new CarcassesFactory(config);
		EnginesFactory engines = new EnginesFactory(config);
		CarsFactory cars = new CarsFactory(engines.getStock(), accessories.getStock(), carcasses.getStock(), config);
		CarsShowroom showroom = new CarsShowroom(cars.getStock(), config);

		Factory[] factories = new Factory[] {accessories, carcasses, engines, cars, showroom};

		System.in.read();
		System.out.println("EXIT");

		for (Factory factory : factories)
			factory.stopFactory();
	}
}
