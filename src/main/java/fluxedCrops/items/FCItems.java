package fluxedCrops.items;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.FluxedCrops;
import fluxedCrops.ModProps;
import fluxedCrops.items.seeds.ItemSeed;
import fluxedCrops.items.upgrades.Upgrade;

public class FCItems {

	public static Item universalSeed = new Item();
	public static Item seed = new ItemSeed();
	public static Item shard = new ItemShard();

	public static Item upgradeEffeciency = new Upgrade();
	public static Item upgradeNight = new Upgrade();
	public static Item upgradeSpeed= new Upgrade();
	

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
		upgradeEffeciency.setCreativeTab(FluxedCrops.tab).setTextureName(ModProps.modid + ":UpgradeEffeciency").setUnlocalizedName("UpgradeEffeciency");
		upgradeNight.setCreativeTab(FluxedCrops.tab).setTextureName(ModProps.modid + ":UpgradeNight").setUnlocalizedName("UpgradeNight");
		upgradeSpeed.setCreativeTab(FluxedCrops.tab).setTextureName(ModProps.modid + ":UpgradeSpeed").setUnlocalizedName("UpgradeSpeed");
		

		GameRegistry.registerItem(upgradeEffeciency, "UpgradeEffeciency");
		GameRegistry.registerItem(upgradeNight, "UpgradeNight");
		GameRegistry.registerItem(upgradeSpeed, "UpgradeSpeed");
		

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
