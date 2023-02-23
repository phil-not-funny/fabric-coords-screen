package com.pnf.fabric.coordsscreen.screens;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.pnf.fabric.coordsscreen.misc.Config;
import com.pnf.fabric.coordsscreen.model.POI;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.SpruceTexts;
import dev.lambdaurora.spruceui.screen.SpruceScreen;
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import dev.lambdaurora.spruceui.widget.text.SpruceTextAreaWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class ListPOIScreen extends SpruceScreen {
	private final Screen parent;

	public ListPOIScreen(@Nullable Screen parent) {
		super(Text.literal("List of POIs"));
		this.parent = parent;
	}

	@Override
	protected void init() {
		super.init();

		int textFieldWidth = (int) (width * (3.0 / 4.0));
		SpruceTextAreaWidget textArea = new SpruceTextAreaWidget(Position.of(width / 2 - textFieldWidth / 2, 30),
				textFieldWidth, height - 30 - 50, Text.of("Text Area"));
		List<String> lines = new ArrayList<String>();
		for (POI poi : Config.getList()) {
			lines.add(poi.toString());
			lines.add("");
		}
		textArea.setLines(lines);
		textArea.setEditable(false);
		textArea.setCursorToStart();

		SpruceButtonWidget doneButton = new SpruceButtonWidget(Position.of(width * (3 / 8), height - 30), 150, 20,
				SpruceTexts.GUI_DONE, btn -> {
					this.client.setScreen(this.parent);
				});
		addDrawableChild(textArea);
		addDrawableChild(doneButton);

	}

	@Override
	public void renderTitle(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 8, 16777215);
	}

}
