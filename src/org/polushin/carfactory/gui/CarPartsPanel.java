package org.polushin.carfactory.gui;

import org.polushin.carfactory.ProductionConfig;
import org.polushin.carfactory.accessories.AccessoriesFactory;
import org.polushin.carfactory.carcass.CarcassesFactory;
import org.polushin.carfactory.engines.EnginesFactory;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Панель поставщиков для запчастей автомобилей.
 */
public class CarPartsPanel extends JPanel {

	private static final int MAX_DELAY = 10000;
	private static final String DISPLAY_FORMAT = "<html>%s: %d/%d,<br>Произведено: %d</html>";
	private static final String ACCESSORIES = "Аксессуары";
	private static final String CARCASSES = "Кузова";
	private static final String ENGINES = "Двигатели";

	private final JLabel accessoriesLabel;
	private final JLabel carcassesLabel;
	private final JLabel enginesLabel;

	private final AccessoriesFactory accessories;
	private final CarcassesFactory carcasses;
	private final EnginesFactory engines;

	private final ProductionConfig config;

	public CarPartsPanel(AccessoriesFactory accessories, CarcassesFactory carcasses, EnginesFactory engines,
	                     ProductionConfig config) {
		this.accessories = accessories;
		this.carcasses = carcasses;
		this.engines = engines;
		this.config = config;

		setPreferredSize(new Dimension(600, 100));
		setLayout(new GridLayout(1, 3));
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		setBorder(BorderFactory.createRaisedBevelBorder());

		JPanel panel = new JPanel();
		panel.setBorder(border);
		panel.setLayout(new GridLayout(2, 1));
		panel.add(accessoriesLabel = new JLabel());
		JSlider accessoriesSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_DELAY, config.accessoriesDelay);
		panel.add(accessoriesSlider);
		accessoriesSlider.addChangeListener(e -> config.accessoriesDelay = accessoriesSlider.getValue());
		add(panel);

		panel = new JPanel();
		panel.setBorder(border);
		panel.setLayout(new GridLayout(2, 1));
		panel.add(carcassesLabel = new JLabel());
		JSlider carcassesSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_DELAY, config.carcassesDelay);
		panel.add(carcassesSlider);
		carcassesSlider.addChangeListener(e -> config.carcassesDelay = carcassesSlider.getValue());
		add(panel);

		panel = new JPanel();
		panel.setBorder(border);
		panel.setLayout(new GridLayout(2, 1));
		panel.add(enginesLabel = new JLabel());
		JSlider enginesSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_DELAY, config.enginesDelay);
		panel.add(enginesSlider);
		enginesSlider.addChangeListener(e -> config.enginesDelay = enginesSlider.getValue());
		add(panel);

		updateStockValues();
		new Timer(MainFrame.REPAINT_DELAY, e -> repaint()).start();
	}

	@Override
	public void repaint() {
		updateStockValues();
		super.repaint();
	}

	private void updateStockValues() {
		if (accessoriesLabel == null)
			return;
		accessoriesLabel.setText(String.format(DISPLAY_FORMAT, ACCESSORIES, accessories.getStock().getSize(),
		                                       config.accessoriesStockSize, accessories.getCount()));
		carcassesLabel.setText(
				String.format(DISPLAY_FORMAT, CARCASSES, carcasses.getStock().getSize(), config.carcassesStockSize,
				              carcasses.getCount()));
		enginesLabel.setText(
				String.format(DISPLAY_FORMAT, ENGINES, engines.getStock().getSize(), config.enginesStockSize,
				              engines.getCount()));
	}
}
