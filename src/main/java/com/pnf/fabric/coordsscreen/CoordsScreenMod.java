package com.pnf.fabric.coordsscreen;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pnf.fabric.coordsscreen.misc.Config;
import com.pnf.fabric.coordsscreen.misc.KeyBindings;
import com.pnf.fabric.coordsscreen.model.POI;

import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import net.fabricmc.api.ClientModInitializer;

public class CoordsScreenMod implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("coords-screen");

	private static CoordsScreenMod INSTANCE;

	public Consumer<SpruceButtonWidget> resetConsumer;

	public static CoordsScreenMod get() {
		return INSTANCE;
	}

	@Override
	public void onInitializeClient() {
		INSTANCE = this;

		System.out.println("Found the following POIs:");
		for (POI poi : Config.getList()) {
			System.out.println(poi.toString());
		}

		new KeyBindings().registerKeybinds();
		LOGGER.info("CoordsScreen initialized");
	}
}
