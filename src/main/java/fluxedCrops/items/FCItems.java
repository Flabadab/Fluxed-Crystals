package fluxedCrops.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.FluxedCrops;
import fluxedCrops.ModProps;
import fluxedCrops.config.ConfigProps;
import fluxedCrops.items.seeds.ItemSeed;

public class FCItems {

	public static Item universalSeed = new Item();
	public static Item seed = new ItemSeed();
	public static Item shard = new ItemShard();

	public static void init() {
		registerItems();
		registerNBT();
		registerRecipes();

	}

	private static void registerRecipes() {

	}

	private static void registerItems() {
		seed.setCreativeTab(FluxedCrops.tab);
		shard.setCreativeTab(FluxedCrops.tab);
		universalSeed.setCreativeTab(FluxedCrops.tab).setTextureName(ModProps.modid + ":seed").setUnlocalizedName("Universal Seed");
		GameRegistry.registerItem(universalSeed, "universalSeed");
		GameRegistry.registerItem(seed, "seed");
		GameRegistry.registerItem(shard, "shard");
	}

	private static void registerNBT() {

	}

	private static void registerDust(Item item, String name, String key, int color) {
		((ItemDust) item).setColor(color);
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + "dust").setCreativeTab(FluxedCrops.tab);
		GameRegistry.registerItem(item, name);
	}
}
