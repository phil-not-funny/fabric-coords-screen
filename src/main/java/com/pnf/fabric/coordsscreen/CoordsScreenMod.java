package com.pnf.fabric.coordsscreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pnf.fabric.coordsscreen.misc.KeyBindings;

public class CoordsScreenMod implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("coords-screen");

	@Override
	public void onInitializeClient() {
		new KeyBindings().registerKeybinds();

		LOGGER.info("CoordsScreen initialized");
	}
}
