package fluxedCrops.items;

import thaumcraft.api.aspects.Aspect;
import thermalfoundation.item.TFItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.ModProps;
import fluxedCrops.blocks.FCBlocks;
import fluxedCrops.config.ConfigProps;
import fluxedCrops.items.seeds.ItemIronSeed;
import fluxedCrops.items.seeds.ItemSeed;

public class FCItems {

	public static Item ironSeed = new ItemSeed(FCBlocks.ironCrop);
	public static Item goldSeed = new ItemSeed(FCBlocks.goldCrop);
	public static Item coalSeed = new ItemSeed(FCBlocks.coalCrop);
	public static Item charcoalSeed = new ItemSeed(FCBlocks.charcoalCrop);
	public static Item copperSeed = new ItemSeed(FCBlocks.copperCrop);
	
	public static void init() {
		registerItems();
		registerNBT();
		registerRecipes();
		
	}

	private static void registerRecipes() {

	}

	private static void registerItems() {
		registerSeed(ironSeed, "Iron Seed", "seedIron", ConfigProps.ironColor);
		registerSeed(goldSeed, "Gold Seed", "seedGold", ConfigProps.goldColor);
		registerSeed(coalSeed, "Coal Seed", "seedCoal", ConfigProps.coalColor);
		registerSeed(charcoalSeed, "Charcoal Seed", "seedCharcoal", ConfigProps.charcoalColor);
		registerSeed(copperSeed, "Copper Seed", "seedCopper", ConfigProps.copperColor);

	}

	private static void registerNBT() {

	}

	private static void registerItem(Item item, String name, String key) {
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + key).setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerItem(item, name);
	}

	private static void registerItem(Item item, String name, String key, CreativeTabs tabs) {
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + key).setCreativeTab(tabs);
		GameRegistry.registerItem(item, name);
	}

	private static void registerShard(Item item, String name, String key, int color) {
		((ItemShard) item).setColor(color);
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":shard").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerItem(item, name);
	}

	private static void registerSeed(Item item, String name, String key, int color) {
		((ItemSeed) item).setColor(color);
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + "seed").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerItem(item, name);
	}
}
