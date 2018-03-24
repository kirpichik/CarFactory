package org.polushin.carfactory;

import org.polushin.carfactory.accessories.AccessoriesFactory;
import org.polushin.carfactory.carcass.CarcassesFactory;
import org.polushin.carfactory.cars.CarsFactory;
import org.polushin.carfactory.dealers.CarsShowroom;
import org.polushin.carfactory.engines.EnginesFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		AccessoriesFactory accessories = new AccessoriesFactory(100, 10);
		CarcassesFactory carcasses = new CarcassesFactory(100, 1);
		EnginesFactory engines = new EnginesFactory(100, 1);
		CarsFactory cars = new CarsFactory(engines.getStock(), accessories.getStock(), carcasses.getStock(), 100, 10);
		CarsShowroom showroom = new CarsShowroom(cars.getStock(), 10);

		Factory[] factories = new Factory[] {accessories, carcasses, engines, cars, showroom};

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		reader.readLine();
		System.out.println("EXIT");

		for (Factory factory : factories)
			factory.stopFactory();
	}
}
