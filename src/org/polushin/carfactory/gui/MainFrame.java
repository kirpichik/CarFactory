package org.polushin.carfactory.gui;

import org.polushin.carfactory.Factory;
import org.polushin.carfactory.ProductionConfig;
import org.polushin.carfactory.accessories.AccessoriesFactory;
import org.polushin.carfactory.carcass.CarcassesFactory;
import org.polushin.carfactory.cars.CarsFactory;
import org.polushin.carfactory.dealers.CarsShowroom;
import org.polushin.carfactory.engines.EnginesFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Главное окно.
 */
public class MainFrame extends JFrame {

	static final int REPAINT_DELAY = 1000;

	public MainFrame(ProductionConfig config) {

		final AccessoriesFactory accessories = new AccessoriesFactory(config);
		final CarcassesFactory carcasses = new CarcassesFactory(config);
		final EnginesFactory engines = new EnginesFactory(config);
		final CarsFactory cars = new CarsFactory(engines.getStock(), accessories.getStock(), carcasses.getStock(),
		                                         config);
		final CarsShowroom showroom = new CarsShowroom(cars.getStock(), config);

		final Factory[] factories = new Factory[] {accessories, carcasses, engines, cars, showroom};

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				for (Factory factory : factories)
					factory.stopFactory();
				System.exit(0);
			}
		});

		GridLayout layout = new GridLayout(3, 1);
		setLayout(layout);

		add(new CarPartsPanel(accessories, carcasses, engines, config));
		add(new CarsFactoryPanel(cars, config));
		add(new DealersPanel(showroom, config));

		validate();
		repaint();
		pack();

		setResizable(false);
		setVisible(true);

	}

}
