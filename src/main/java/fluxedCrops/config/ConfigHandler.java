package fluxedCrops.config;

import java.io.File;

import com.sun.xml.internal.fastinfoset.algorithm.HexadecimalEncodingAlgorithm;

import net.minecraftforge.common.config.Configuration;

/**
 * Created by Jared on 11/2/2014.
 */
public class ConfigHandler {

	public static final String seedColor = "Seed Color";

	public static void init(File file) {
		Configuration config = new Configuration(file);

		config.load();

		ConfigProps.ironColor = config.get(seedColor, "Iron Shard", 0xd6b781).getInt(0xd6b781);
		ConfigProps.goldColor = config.get(seedColor, "Gold Shard", 0xFFD700).getInt(0xFFD700);
		ConfigProps.coalColor = config.get(seedColor, "Coal Shard", 0x141414).getInt(0x141414);
		ConfigProps.charcoalColor = config.get(seedColor, "Charcoal Shard", 0x252017).getInt(0x252017);
		ConfigProps.copperColor = config.get(seedColor, "Copper Shard", 0xB87333).getInt(0xB87333);

		config.save();

	}
}
