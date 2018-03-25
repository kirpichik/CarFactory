package org.polushin.carfactory.gui;

import org.polushin.carfactory.ProductionConfig;
import org.polushin.carfactory.dealers.CarsShowroom;

import javax.swing.*;
import java.awt.*;

/**
 * Панель продавцов.
 */
public class DealersPanel extends JPanel {

	private static final int MAX_DELAY = 10000;
	private static final String DISPLAY_FORMAT = "%s: %d, $%d";
	private static final String CARS = "Продажи";

	private final JLabel showroomLabel;
	private final CarsShowroom showroom;

	public DealersPanel(CarsShowroom showroom, ProductionConfig config) {
		this.showroom = showroom;

		setPreferredSize(new Dimension(600, 100));
		setLayout(new GridLayout(1, 2));
		setBorder(BorderFactory.createRaisedBevelBorder());

		add(showroomLabel = new JLabel());
		JSlider carsSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_DELAY, config.saleDelay);
		add(carsSlider);
		carsSlider.addChangeListener(e -> config.saleDelay = carsSlider.getValue());

		updateStockValues();
		new Timer(MainFrame.REPAINT_DELAY, e -> repaint()).start();
	}

	@Override
	public void repaint() {
		updateStockValues();
		super.repaint();
	}

	private void updateStockValues() {
		if (showroomLabel == null)
			return;
		showroomLabel.setText(String.format(DISPLAY_FORMAT, CARS, showroom.getCount(), showroom.getCash()));
	}

}
