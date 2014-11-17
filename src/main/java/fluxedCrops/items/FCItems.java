package fluxedCrops.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.ModProps;
import fluxedCrops.api.SeedBase;
import fluxedCrops.config.ConfigProps;
import fluxedCrops.items.seeds.ItemSeed;

public class FCItems {

	public static Item seedIron = new ItemSeed();
	public static Item seedGold = new ItemSeed();
	public static Item seedCoal = new ItemSeed();
	public static Item seedCharcoal = new ItemSeed();
	public static Item seedRedstone = new ItemSeed();
	public static Item seedDiamond = new ItemSeed();

	public static Item seedCopper = new ItemSeed();
	public static Item seedTin = new ItemSeed();
	public static Item seedSilver = new ItemSeed();
	public static Item seedLead = new ItemSeed();
	public static Item seedFerrous = new ItemSeed();
	public static Item seedShiny = new ItemSeed();
	public static Item seedMana = new ItemSeed();
	public static Item seedElectrum = new ItemSeed();
	public static Item seedInvar = new ItemSeed();
	public static Item seedTinkers = new ItemSeed();
	public static Item seedSignalum = new ItemSeed();
	public static Item seedLumium = new ItemSeed();
	public static Item seedEnderium = new ItemSeed();

	public static Item dustEmerald = new ItemDust();
	public static Item dustDiamond = new ItemDust();

	public static void init() {
		registerItems();
		registerNBT();
		registerRecipes();

	}

	private static void registerRecipes() {

	}

	private static void registerItems() {
		registerSeed(seedIron, "Iron Seed", "seedIron", ConfigProps.ironColor);
		registerSeed(seedGold, "Gold Seed", "seedGold", ConfigProps.goldColor);
		registerSeed(seedCoal, "Coal Seed", "seedCoal", ConfigProps.coalColor);
		registerSeed(seedCharcoal, "Charcoal Seed", "seedCharcoal", ConfigProps.charcoalColor);
		registerSeed(seedRedstone, "Redstone Seed", "seedRedstone", ConfigProps.colorRedstone);
		registerSeed(seedDiamond, "Diamond Seed", "seedDiamond", ConfigProps.colorDiamond);

		registerSeed(seedCopper, "Copper Seed", "seedCopper", ConfigProps.colorCopper);
		registerSeed(seedTin, "Tin Seed", "seedTin", ConfigProps.colorTin);
		registerSeed(seedSilver, "Silver Seed", "seedSilver", ConfigProps.colorSilver);
		registerSeed(seedLead, "Lead Seed", "seedLead", ConfigProps.colorLead);
		registerSeed(seedFerrous, "Ferrous Seed", "seedFerrous", ConfigProps.colorFerrous);
		registerSeed(seedShiny, "Shiny Seed", "seedShiny", ConfigProps.colorShiny);
		registerSeed(seedMana, "Mana Seed", "seedMana", ConfigProps.colorMana);
		registerSeed(seedElectrum, "Electrum Seed", "seedElectrum", ConfigProps.colorElectrum);
		registerSeed(seedInvar, "Invar  Seed", "seedInvar", ConfigProps.colorInvar);
		registerSeed(seedTinkers, "Tinkers Alloy Seed", "seedTinkersAlloy", ConfigProps.colorTinkers);
		registerSeed(seedSignalum, "Signalum Seed", "seedSignalum", ConfigProps.colorSignalum);
		registerSeed(seedEnderium, "Enderium Seed", "seedEnderium", ConfigProps.colorEnderium);

		registerDust(dustEmerald, "Emerald Dust", "dustEmerald", ConfigProps.colorEmerald);
		registerDust(dustDiamond, "Diamond Dust", "dustDiamond", ConfigProps.colorDiamond);

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
		((SeedBase) item).setColor(color);
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + "seed").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerItem(item, name);
	}

	private static void registerDust(Item item, String name, String key, int color) {
		((ItemDust) item).setColor(color);
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + "dust").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerItem(item, name);
	}
}
