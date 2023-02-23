package com.pnf.fabric.coordsscreen.misc;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pnf.fabric.coordsscreen.model.POI;

import net.fabricmc.loader.api.FabricLoader;

public class Config {
	private static final File configFile = new File(FabricLoader.getInstance().getConfigDir().toString() + "/coordsscreen.json");
	private static ArrayList<POI> poiList;

	@SuppressWarnings("deprecation")
	public static List<POI> getList() {
		ArrayList<POI> list = new ArrayList<POI>();
		JsonParser jsonParser = new JsonParser();

		try (FileReader reader = new FileReader(configFile)) {
			Object obj = jsonParser.parse(reader);
			JsonArray jsonArr = (JsonArray) obj;

			jsonArr.forEach((o) -> {
				JsonObject parent = o.getAsJsonObject();
				list.add(new POI(parent.get("name").getAsString(), parent.get("x").getAsFloat(),
						parent.get("y").getAsFloat(), parent.get("z").getAsFloat(),
						MinecraftLevels.valueOf(parent.get("level").getAsString())));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		poiList = list;
		return list;
	}

	public static void saveList() {
		if(!configFile.exists())
			try {
				configFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		JsonArray jsonArr = new JsonArray();
		for (POI poi : poiList) {
			JsonObject obj = new JsonObject();
			obj.addProperty("name", poi.getName());
			obj.addProperty("x", poi.getX());
			obj.addProperty("y", poi.getY());
			obj.addProperty("z", poi.getZ());
			obj.addProperty("level", poi.getLevel().getText().getString());

			jsonArr.add(obj);
		}

		try (FileWriter writer = new FileWriter(configFile)) {
			writer.write(jsonArr.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addPOI(POI poi) {
		poiList.add(poi);
		saveList();
	}
	
	public static List<POI> getPOIList() {
		return poiList;
	}

}
