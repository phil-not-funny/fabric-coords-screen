package com.pnf.fabric.coordsscreen.misc;

import org.jetbrains.annotations.NotNull;

import dev.lambdaurora.spruceui.util.Nameable;
import net.minecraft.text.Text;

/**
 * Represents a dummy enum.
 *
 * @author LambdAurora
 */
public enum MinecraftLevels implements Nameable {
    OVERWORLD,
    NETHER,
    THE_END;

    private final Text text;

    MinecraftLevels() {
        this.text = Text.literal(this.getName());
    }

    /**
     * Returns the next enum value available.
     *
     * @return The next available enum value.
     */
    public @NotNull MinecraftLevels next() {
        var v = values();
        if (v.length == this.ordinal() + 1)
            return v[0];
        return v[this.ordinal() + 1];
    }

    /**
     * Gets the text of this enum value.
     *
     * @return The text of this enum value.
     */
    public @NotNull Text getText() {
        return this.text;
    }

    @Override
    public @NotNull String getName() {
        return this.name().replace("_", " ");
    }
}