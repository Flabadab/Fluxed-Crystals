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

        config.save();

    }
}
