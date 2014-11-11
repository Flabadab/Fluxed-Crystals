package fluxedCrops.config;

import com.typesafe.config.ConfigParseOptions;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Jared on 11/2/2014.
 */
public class ConfigHandler {

    public static final String shardColor = "Shard Color";

    public static void init(File file) {
        Configuration config = new Configuration(file);

        config.load();

        ConfigProps.ironColor = config.get(shardColor, "Iron Shard", 0xd6b781).getInt(0xd6b781);
        ConfigProps.goldColor = config.get(shardColor, "Gold Shard", 0xFFD700).getInt(0xFFD700);
        ConfigProps.coalColor = config.get(shardColor, "Coal Shard", 0x141414).getInt(0x141414);
        ConfigProps.charcoalColor= config.get(shardColor, "Charcoal Shard", 0x252017).getInt(0x252017);
        
        
        
        config.save();

    }
}
