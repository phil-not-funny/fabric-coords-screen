package com.pnf.fabric.coordsscreen.model;

import java.util.Objects;

import com.pnf.fabric.coordsscreen.misc.MinecraftLevels;

public class POI {
	private String name;
	private float x;
	private float y;
	private float z;
	private MinecraftLevels level;

	public POI(String name, float x, float y, float z, MinecraftLevels level) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.level = level;
	}

	@Override
	public String toString() {
		return name + " (x:" + x + " / y:" + y +" / z: " + z + ") in " + level.getText().getString();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	public MinecraftLevels getLevel() {
		return level;
	}
	public void setLevel(MinecraftLevels level) {
		this.level = level;
	}

	@Override
	public int hashCode() {
		return Objects.hash(level, name, x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		POI other = (POI) obj;
		return level == other.level && Objects.equals(name, other.name)
				&& Float.floatToIntBits(x) == Float.floatToIntBits(other.x)
				&& Float.floatToIntBits(y) == Float.floatToIntBits(other.y)
				&& Float.floatToIntBits(z) == Float.floatToIntBits(other.z);
	}
}
