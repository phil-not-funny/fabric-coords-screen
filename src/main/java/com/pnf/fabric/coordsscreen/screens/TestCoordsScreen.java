package com.pnf.fabric.coordsscreen.screens;

import org.jetbrains.annotations.Nullable;

import com.pnf.fabric.coordsscreen.CoordsScreenMod;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.SpruceTexts;
import dev.lambdaurora.spruceui.screen.SpruceScreen;
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import dev.lambdaurora.spruceui.widget.SpruceLabelWidget;
import dev.lambdaurora.spruceui.widget.container.SpruceContainerWidget;
import dev.lambdaurora.spruceui.widget.container.tabbed.SpruceTabbedWidget;
import dev.lambdaurora.spruceui.widget.text.SpruceTextAreaWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class TestCoordsScreen extends SpruceScreen {
	private final Screen parent;
	private SpruceTabbedWidget tabbedWidget;
	
	public TestCoordsScreen(@Nullable Screen parent) {
		super(Text.literal("Search POIs"));
		this.parent = parent;
	}
	
	@Override
	protected void init() {
		super.init();
        this.tabbedWidget = new SpruceTabbedWidget(Position.of(this, 0, 4), this.width, this.height - 35 - 4, this.title);
        this.tabbedWidget.addTabEntry(Text.literal("Hello World"), null, (width, height) -> {
            var container = new SpruceContainerWidget(Position.origin(), width, height);
            container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 16),
                        Text.literal("Hello World!").formatted(Formatting.WHITE),
                        containerWidth, true));
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 48),
                        Text.literal("This is a tabbed widget. You can switch tabs by using the list on the left.\n" +
                                "It also allows quite a good controller support and arrow key navigation.")
                                .formatted(Formatting.WHITE),
                        containerWidth, true));
            });
            return container;
        });
        this.tabbedWidget.addSeparatorEntry(Text.literal("Separator"));
        this.tabbedWidget.addTabEntry(Text.literal("Option Test"), Text.literal("useful for config stuff.").formatted(Formatting.GRAY),
                (width, height) -> CoordsScreenMod.get().buildOptionList(Position.origin(), width, height));
        this.tabbedWidget.addTabEntry(Text.literal("Text Area"), Text.literal("to edit stuff on multiple lines.").formatted(Formatting.GRAY),
                (width, height) -> CoordsScreenMod.buildTextAreaContainer(Position.origin(), width, height,
                        textArea -> {
                        }, null));
        this.addDrawableChild(this.tabbedWidget);

        // Add done button.
        this.addDrawableChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 75, this.height - 29), 150, 20, SpruceTexts.GUI_DONE,
                btn -> this.client.setScreen(this.parent)).asVanilla());
	}
	
	
}
