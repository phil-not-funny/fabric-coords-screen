package com.pnf.fabric.coordsscreen.misc;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public class KeyBindings {
	public static final String KEYBIND_CATEGORY = "CoordsScreen Mod";
	public static KeyBinding keyBinding;
	
	public KeyBindings() {
		createKeybinds();
	}
	
	public void createKeybinds() {
		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("Open Menu",
				InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_COMMA, KEYBIND_CATEGORY));
	}
	
	public void registerKeybinds() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (keyBinding.wasPressed()) {
				client.player.sendMessage(Text.literal("Key COMMA was pressed"), false);
			}
		});
	}
}
