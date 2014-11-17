package fluxedCrops.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.ModProps;
import fluxedCrops.api.SeedBase;
import fluxedCrops.config.ConfigProps;
import fluxedCrops.items.seeds.ItemSeed;

public class FCItems {

	public static Item seed = new ItemSeed();
	
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
		
		GameRegistry.registerItem(seed, "seed");
		
//		registerSeed("Iron Seed", "seedIron", ConfigProps.ironColor);
//		registerSeed("Gold Seed", "seedGold", ConfigProps.goldColor);
//		registerSeed("Coal Seed", "seedCoal", ConfigProps.coalColor);
//		registerSeed("Charcoal Seed", "seedCharcoal", ConfigProps.charcoalColor);
//		registerSeed("Redstone Seed", "seedRedstone", ConfigProps.colorRedstone);
//		registerSeed("Diamond Seed", "seedDiamond", ConfigProps.colorDiamond);
//
//		registerSeed("Copper Seed", "seedCopper", ConfigProps.colorCopper);
//		registerSeed("Tin Seed", "seedTin", ConfigProps.colorTin);
//		registerSeed("Silver Seed", "seedSilver", ConfigProps.colorSilver);
//		registerSeed("Lead Seed", "seedLead", ConfigProps.colorLead);
//		registerSeed("Ferrous Seed", "seedFerrous", ConfigProps.colorFerrous);
//		registerSeed("Shiny Seed", "seedShiny", ConfigProps.colorShiny);
//		registerSeed("Mana Seed", "seedMana", ConfigProps.colorMana);
//		registerSeed("Electrum Seed", "seedElectrum", ConfigProps.colorElectrum);
//		registerSeed("Invar  Seed", "seedInvar", ConfigProps.colorInvar);
//		registerSeed("Tinkers Alloy Seed", "seedTinkersAlloy", ConfigProps.colorTinkers);
//		registerSeed("Signalum Seed", "seedSignalum", ConfigProps.colorSignalum);
//		registerSeed("Enderium Seed", "seedEnderium", ConfigProps.colorEnderium);

		registerDust(dustEmerald, "Emerald Dust", "dustEmerald", ConfigProps.colorEmerald);
		registerDust(dustDiamond, "Diamond Dust", "dustDiamond", ConfigProps.colorDiamond);

	}

	private static void registerNBT() {

	}

	private static void registerDust(Item item, String name, String key, int color) {
		((ItemDust) item).setColor(color);
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + "dust").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerItem(item, name);
	}
}
