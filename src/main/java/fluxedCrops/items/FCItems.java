package fluxedCrops.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import thermalfoundation.item.TFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.ModProps;
import fluxedCrops.blocks.FCBlocks;
import fluxedCrops.config.ConfigProps;
import fluxedCrops.items.seeds.ItemSeed;

public class FCItems {

<<<<<<< HEAD
	public static Item ironSeed = new ItemSeed(FCBlocks.ironCrop, TFItems.dustIron);
	public static Item goldSeed = new ItemSeed(FCBlocks.goldCrop, TFItems.dustIron);
	public static Item coalSeed = new ItemSeed(FCBlocks.coalCrop, TFItems.dustIron);
	public static Item charcoalSeed = new ItemSeed(FCBlocks.charcoalCrop, TFItems.dustIron);
	public static Item copperSeed = new ItemSeed(FCBlocks.copperCrop, TFItems.dustIron);
	
=======
	public static Item ironShard = new ItemShard();
	public static Item ironSeed = new ItemSeed(FCBlocks.ironCrop);

	public static Item goldShard = new ItemShard();
	public static Item goldSeed = new ItemSeed(FCBlocks.goldCrop);

	public static Item coalShard = new ItemShard();
	public static Item coalSeed = new ItemSeed(FCBlocks.coalCrop);

	public static Item charcoalShard = new ItemShard();
	public static Item charcoalSeed = new ItemSeed(FCBlocks.charcoalCrop);

>>>>>>> parent of aa4966c... thaumcraft support still WIP
	public static void init() {
		registerItems();
		registerNBT();
		registerRecipes();
	}

	private static void registerRecipes() {

	}

	private static void registerItems() {
		registerShard(ironShard, "Iron Shard", "ironShard", ConfigProps.ironColor);
		registerSeed(ironSeed, "Iron Seed", "seedIron", ConfigProps.ironColor);
		registerShard(goldShard, "Gold Shard", "goldShard", ConfigProps.goldColor);
		registerSeed(goldSeed, "Gold Seed", "seedGold", ConfigProps.goldColor);
		registerShard(coalShard, "Coal Shard", "coalShard", ConfigProps.coalColor);
		registerSeed(coalSeed, "Coal Seed", "seedCoal", ConfigProps.coalColor);
		registerShard(charcoalShard, "Charcoal Shard", "charcoalShard", ConfigProps.charcoalColor);
		registerSeed(charcoalSeed, "Charcoal Seed", "seedCharcoal", ConfigProps.charcoalColor);
		

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
