package com.pnf.fabric.coordsscreen.screens;

import org.jetbrains.annotations.Nullable;

import com.pnf.fabric.coordsscreen.misc.Config;
import com.pnf.fabric.coordsscreen.misc.MinecraftLevels;
import com.pnf.fabric.coordsscreen.model.POI;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.option.SpruceCyclingOption;
import dev.lambdaurora.spruceui.option.SpruceFloatInputOption;
import dev.lambdaurora.spruceui.option.SpruceSimpleActionOption;
import dev.lambdaurora.spruceui.screen.SpruceScreen;
import dev.lambdaurora.spruceui.widget.SpruceLabelWidget;
import dev.lambdaurora.spruceui.widget.container.SpruceContainerWidget;
import dev.lambdaurora.spruceui.widget.container.SpruceOptionListWidget;
import dev.lambdaurora.spruceui.widget.text.SpruceNamedTextFieldWidget;
import dev.lambdaurora.spruceui.widget.text.SpruceTextFieldWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class AddPOIScreen extends SpruceScreen {
	private Screen parent;
	private float xInValue;
	private float yInValue;
	private float zInValue;
	private MinecraftLevels worldInValue = MinecraftLevels.OVERWORLD;

	public AddPOIScreen(@Nullable Screen parent) {
		super(Text.literal("Add a POI"));
		this.parent = parent;
	}

	@Override
	protected void init() {
		super.init();

		var container = new SpruceContainerWidget(Position.origin(), width, 25);
		container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
			widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 3),
					Text.literal("Add a POI to your list of POIs; ESC to Cancel")
							.formatted(Formatting.WHITE),
					containerWidth, true));
		});

		SpruceNamedTextFieldWidget textfield = new SpruceNamedTextFieldWidget(
				new SpruceTextFieldWidget(Position.of(5, 12), width / 2 - 10, 20, Text.literal("Name of POI")));

		SpruceOptionListWidget list = new SpruceOptionListWidget(Position.of(0, 45), width, height - 25);

		list.addSingleOptionEntry(
				new SpruceFloatInputOption("x-Coordinate", () -> xInValue, value -> xInValue = value, null));
		list.addSingleOptionEntry(
				new SpruceFloatInputOption("y-Coordinate", () -> yInValue, value -> yInValue = value, null));
		list.addSingleOptionEntry(
				new SpruceFloatInputOption("z-Coordinate", () -> zInValue, value -> zInValue = value, null));
		list.addSingleOptionEntry(new SpruceCyclingOption("World",
				amount -> this.worldInValue = this.worldInValue.next(),
				option -> option.getDisplayText(this.worldInValue.getText()), Text.of("The world of the coordinates")));
		list.addSingleOptionEntry(SpruceSimpleActionOption.of("Pick Current", btn -> {
			var position = MinecraftClient.getInstance().player.getPos();
			xInValue = (float) position.x;
			yInValue = (float) position.y;
			zInValue = (float) position.z;
		}, null));
		list.addSingleOptionEntry(SpruceSimpleActionOption.of("Create", btn -> {
			if(!textfield.getText().isBlank()) {
				POI poi = new POI(textfield.getText(), xInValue, yInValue, zInValue, worldInValue);
				Config.addPOI(poi);
				System.out.println(poi.toString());
				this.client.setScreen(this.parent);
			}
		}, null));

		addDrawableChild(container);
		addDrawableChild(textfield);
		addDrawableChild(list);
		// container.setBackground(DrawableHelper.fillGradient(matrices, 0, 0,
		// this.width, this.height, -1072689136, -804253680));
	}
	
}