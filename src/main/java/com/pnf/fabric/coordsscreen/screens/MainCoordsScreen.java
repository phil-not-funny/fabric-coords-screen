package com.pnf.fabric.coordsscreen.screens;

import org.jetbrains.annotations.Nullable;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.SpruceTexts;
import dev.lambdaurora.spruceui.screen.SpruceScreen;
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class MainCoordsScreen extends SpruceScreen {

	private final Screen parent;

    public MainCoordsScreen(@Nullable Screen parent) {
        super(Text.literal("Coords Screen Fabric Menu"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        int startY = 25;
        this.addDrawableChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 100, startY ), 200, 20, Text.literal("Add a POI"),
				btn -> 
				  this.client.setScreen(new AddPOIScreen(this))));
        this.addDrawableChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 100, startY += 25), 200, 20, Text.literal("List of POIs"),
                btn -> this.client.setScreen(new ListPOIScreen(this))));

        // Add done button.
        this.addDrawableChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 75, this.height - 29), 150, 20, SpruceTexts.GUI_DONE,
                btn -> this.client.setScreen(this.parent)));
    }

    @Override
	public void renderTitle(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 8, 16777215);
    }
}
