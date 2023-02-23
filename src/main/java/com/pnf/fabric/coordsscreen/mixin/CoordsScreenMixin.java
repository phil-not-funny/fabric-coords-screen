package com.pnf.fabric.coordsscreen.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.pnf.fabric.coordsscreen.CoordsScreenMod;

import net.minecraft.client.gui.screen.TitleScreen;

@Mixin(TitleScreen.class)
public class CoordsScreenMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		CoordsScreenMod.LOGGER.info("This line is printed by an example mod mixin!");
	}
}
