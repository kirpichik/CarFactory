package org.polushin.carfactory.gui;

import org.polushin.carfactory.ProductionConfig;
import org.polushin.carfactory.cars.CarsFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Панель фабрики автомобилей.
 */
public class CarsFactoryPanel extends JPanel {

	private static final int MAX_DELAY = 10000;
	private static final String DISPLAY_FORMAT = "<html>%s: %d/%d,<br>Произведено: %d</html>";
	private static final String CARS = "Автомобили";

	private final JLabel carsLabel;
	private final CarsFactory cars;

	private final ProductionConfig config;

	public CarsFactoryPanel(CarsFactory cars, ProductionConfig config) {
		this.cars = cars;
		this.config = config;

		setPreferredSize(new Dimension(600, 100));
		setLayout(new GridLayout(1, 2));
		setBorder(BorderFactory.createRaisedBevelBorder());

		add(carsLabel = new JLabel());
		JSlider carsSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_DELAY, config.carsDelay);
		add(carsSlider);
		carsSlider.addChangeListener(e -> config.carsDelay = carsSlider.getValue());

		updateStockValues();
		new Timer(MainFrame.REPAINT_DELAY, e -> repaint()).start();
	}

	@Override
	public void repaint() {
		updateStockValues();
		super.repaint();
	}

	private void updateStockValues() {
		if (carsLabel == null)
			return;
		carsLabel.setText(
				String.format(DISPLAY_FORMAT, CARS, cars.getStock().getSize(), config.carsStockSize, cars.getCount()));
	}

}
