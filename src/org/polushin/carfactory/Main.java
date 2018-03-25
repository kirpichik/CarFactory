package org.polushin.carfactory;

import org.polushin.carfactory.gui.MainFrame;

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

		new MainFrame(config);

	}
}
