package fluxedCrops.handlers;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.ModProps;
import fluxedCrops.api.CropBase;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.recipe.SeedCropRecipe;
import fluxedCrops.blocks.crops.BlockCrop;
import fluxedCrops.items.seeds.ItemSeed;

public class ThaumcraftHandler {

	public static void init() {
		registerAspects();
		registerOthers();

	}

	public static ItemStack[] essentia = new ItemStack[Aspect.aspects.size()];
	public static Block[] essentiaCrops = new BlockCrop[Aspect.aspects.size()];
	public static Item[] essentiaSeeds = new Item[Aspect.aspects.size()];

	public static ItemStack podAir;
	public static ItemStack podEarth;
	public static ItemStack podFire;
	public static ItemStack podWater;
	public static ItemStack podOrder;
	public static ItemStack podEntropy;

	public static ItemStack podVoid;
	public static ItemStack podLight;
	public static ItemStack podWeather;
	public static ItemStack podMotion;
	public static ItemStack podCold;
	public static ItemStack podCrystal;
	public static ItemStack podLife;
	public static ItemStack podPoison;
	public static ItemStack podEnergy;
	public static ItemStack podExchange;

	public static ItemStack podMetal;
	public static ItemStack podDeath;
	public static ItemStack podFlight;
	public static ItemStack podDarkness;
	public static ItemStack podSoul;
	public static ItemStack podHeal;
	public static ItemStack podTravel;
	public static ItemStack podEldritch;
	public static ItemStack podMagic;
	public static ItemStack podAura;
	public static ItemStack podTaint;
	public static ItemStack podSlime;
	public static ItemStack podPlant;
	public static ItemStack podTree;
	public static ItemStack podBeast;
	public static ItemStack podFlesh;
	public static ItemStack podUndead;
	public static ItemStack podMind;
	public static ItemStack podSenses;
	public static ItemStack podMan;
	public static ItemStack podCrop;
	public static ItemStack podMine;
	public static ItemStack podTool;
	public static ItemStack podHarvest;
	public static ItemStack podWeapon;
	public static ItemStack podArmor;
	public static ItemStack podHunger;
	public static ItemStack podGreed;
	public static ItemStack podCraft;
	public static ItemStack podCloth;
	public static ItemStack podMechanism;
	public static ItemStack podTrap;

	public static ItemSeed seedAir;
	public static ItemSeed seedEarth;
	public static ItemSeed seedFire;
	public static ItemSeed seedWater;
	public static ItemSeed seedOrder;
	public static ItemSeed seedEntropy;

	public static ItemSeed seedVoid;
	public static ItemSeed seedLight;
	public static ItemSeed seedWeather;
	public static ItemSeed seedMotion;
	public static ItemSeed seedCold;
	public static ItemSeed seedCrystal;
	public static ItemSeed seedLife;
	public static ItemSeed seedPoison;
	public static ItemSeed seedEnergy;
	public static ItemSeed seedExchange;

	public static ItemSeed seedMetal;
	public static ItemSeed seedDeath;
	public static ItemSeed seedFlight;
	public static ItemSeed seedDarkness;
	public static ItemSeed seedSoul;
	public static ItemSeed seedHeal;
	public static ItemSeed seedTravel;
	public static ItemSeed seedEldritch;
	public static ItemSeed seedMagic;
	public static ItemSeed seedAura;
	public static ItemSeed seedTaint;
	public static ItemSeed seedSlime;
	public static ItemSeed seedPlant;
	public static ItemSeed seedTree;
	public static ItemSeed seedBeast;
	public static ItemSeed seedFlesh;
	public static ItemSeed seedUndead;
	public static ItemSeed seedMind;
	public static ItemSeed seedSenses;
	public static ItemSeed seedMan;
	public static ItemSeed seedCrop;
	public static ItemSeed seedMine;
	public static ItemSeed seedTool;
	public static ItemSeed seedHarvest;
	public static ItemSeed seedWeapon;
	public static ItemSeed seedArmor;
	public static ItemSeed seedHunger;
	public static ItemSeed seedGreed;
	public static ItemSeed seedCraft;
	public static ItemSeed seedCloth;
	public static ItemSeed seedMechanism;
	public static ItemSeed seedTrap;

	public static Block cropAir;
	public static Block cropEarth;
	public static Block cropFire;
	public static Block cropWater;
	public static Block cropOrder;
	public static Block cropEntropy;

	public static Block cropVoid;
	public static Block cropLight;
	public static Block cropWeather;
	public static Block cropMotion;
	public static Block cropCold;
	public static Block cropCrystal;
	public static Block cropLife;
	public static Block cropPoison;
	public static Block cropEnergy;
	public static Block cropExchange;

	public static Block cropMetal;
	public static Block cropDeath;
	public static Block cropFlight;
	public static Block cropDarkness;
	public static Block cropSoul;
	public static Block cropHeal;
	public static Block cropTravel;
	public static Block cropEldritch;
	public static Block cropMagic;
	public static Block cropAura;
	public static Block cropTaint;
	public static Block cropSlime;
	public static Block cropPlant;
	public static Block cropTree;
	public static Block cropBeast;
	public static Block cropFlesh;
	public static Block cropUndead;
	public static Block cropMind;
	public static Block cropSenses;
	public static Block cropMan;
	public static Block cropCrop;
	public static Block cropMine;
	public static Block cropTool;
	public static Block cropHarvest;
	public static Block cropWeapon;
	public static Block cropArmor;
	public static Block cropHunger;
	public static Block cropGreed;
	public static Block cropCraft;
	public static Block cropCloth;
	public static Block cropMechanism;
	public static Block cropTrap;

	private static void registerAspects() {
		podAir = ItemApi.getItem("itemCrystalEssence", 0);
		podEarth = ItemApi.getItem("itemCrystalEssence", 1);
		podFire = ItemApi.getItem("itemCrystalEssence", 2);
		podWater = ItemApi.getItem("itemCrystalEssence", 3);
		podOrder = ItemApi.getItem("itemCrystalEssence", 4);
		podEntropy = ItemApi.getItem("itemCrystalEssence", 5);

		podVoid = ItemApi.getItem("itemCrystalEssence", 6);
		podLight = ItemApi.getItem("itemCrystalEssence", 7);
		podWeather = ItemApi.getItem("itemCrystalEssence", 8);
		podMotion = ItemApi.getItem("itemCrystalEssence", 9);
		podCold = ItemApi.getItem("itemCrystalEssence", 10);
		podCrystal = ItemApi.getItem("itemCrystalEssence", 11);
		podLife = ItemApi.getItem("itemCrystalEssence", 12);
		podPoison = ItemApi.getItem("itemCrystalEssence", 13);
		podEnergy = ItemApi.getItem("itemCrystalEssence", 14);
		podExchange = ItemApi.getItem("itemCrystalEssence", 15);

		podMetal = ItemApi.getItem("itemCrystalEssence", 16);
		podDeath = ItemApi.getItem("itemCrystalEssence", 17);
		podFlight = ItemApi.getItem("itemCrystalEssence", 18);
		podDarkness = ItemApi.getItem("itemCrystalEssence", 19);
		podSoul = ItemApi.getItem("itemCrystalEssence", 20);
		podHeal = ItemApi.getItem("itemCrystalEssence", 21);
		podTravel = ItemApi.getItem("itemCrystalEssence", 22);
		podEldritch = ItemApi.getItem("itemCrystalEssence", 23);
		podMagic = ItemApi.getItem("itemCrystalEssence", 24);
		podAura = ItemApi.getItem("itemCrystalEssence", 25);
		podTaint = ItemApi.getItem("itemCrystalEssence", 26);
		podSlime = ItemApi.getItem("itemCrystalEssence", 27);
		podPlant = ItemApi.getItem("itemCrystalEssence", 28);
		podTree = ItemApi.getItem("itemCrystalEssence", 29);
		podBeast = ItemApi.getItem("itemCrystalEssence", 30);
		podFlesh = ItemApi.getItem("itemCrystalEssence", 31);
		podUndead = ItemApi.getItem("itemCrystalEssence", 32);
		podMind = ItemApi.getItem("itemCrystalEssence", 33);
		podSenses = ItemApi.getItem("itemCrystalEssence", 34);
		podMan = ItemApi.getItem("itemCrystalEssence", 35);
		podCrop = ItemApi.getItem("itemCrystalEssence", 36);
		podMine = ItemApi.getItem("itemCrystalEssence", 37);
		podTool = ItemApi.getItem("itemCrystalEssence", 38);
		podHarvest = ItemApi.getItem("itemCrystalEssence", 39);
		podWeapon = ItemApi.getItem("itemCrystalEssence", 40);
		podArmor = ItemApi.getItem("itemCrystalEssence", 41);
		podHunger = ItemApi.getItem("itemCrystalEssence", 42);
		podGreed = ItemApi.getItem("itemCrystalEssence", 43);
		podCraft = ItemApi.getItem("itemCrystalEssence", 44);
		podCloth = ItemApi.getItem("itemCrystalEssence", 45);
		podMechanism = ItemApi.getItem("itemCrystalEssence", 46);
		podTrap = ItemApi.getItem("itemCrystalEssence", 47);

		seedAir = new ItemSeed();
		seedEarth = new ItemSeed();
		seedFire = new ItemSeed();
		seedWater = new ItemSeed();
		seedOrder = new ItemSeed();
		seedEntropy = new ItemSeed();

		seedVoid = new ItemSeed();
		seedLight = new ItemSeed();
		seedWeather = new ItemSeed();
		seedMotion = new ItemSeed();
		seedCold = new ItemSeed();
		seedCrystal = new ItemSeed();
		seedLife = new ItemSeed();
		seedPoison = new ItemSeed();
		seedEnergy = new ItemSeed();
		seedExchange = new ItemSeed();

		seedMetal = new ItemSeed();
		seedDeath = new ItemSeed();
		seedFlight = new ItemSeed();
		seedDarkness = new ItemSeed();
		seedSoul = new ItemSeed();
		seedHeal = new ItemSeed();
		seedTravel = new ItemSeed();
		seedEldritch = new ItemSeed();
		seedMagic = new ItemSeed();
		seedAura = new ItemSeed();
		seedTaint = new ItemSeed();
		seedSlime = new ItemSeed();
		seedPlant = new ItemSeed();
		seedTree = new ItemSeed();
		seedBeast = new ItemSeed();
		seedFlesh = new ItemSeed();
		seedUndead = new ItemSeed();
		seedMind = new ItemSeed();
		seedSenses = new ItemSeed();
		seedMan = new ItemSeed();
		seedCrop = new ItemSeed();
		seedMine = new ItemSeed();
		seedTool = new ItemSeed();
		seedHarvest = new ItemSeed();
		seedWeapon = new ItemSeed();
		seedArmor = new ItemSeed();
		seedHunger = new ItemSeed();
		seedGreed = new ItemSeed();
		seedCraft = new ItemSeed();
		seedCloth = new ItemSeed();
		seedMechanism = new ItemSeed();
		seedTrap = new ItemSeed();

		cropAir = new BlockCrop();
		cropEarth = new BlockCrop();
		cropFire = new BlockCrop();
		cropWater = new BlockCrop();
		cropOrder = new BlockCrop();
		cropEntropy = new BlockCrop();

		cropVoid = new BlockCrop();
		cropLight = new BlockCrop();
		cropWeather = new BlockCrop();
		cropMotion = new BlockCrop();
		cropCold = new BlockCrop();
		cropCrystal = new BlockCrop();
		cropLife = new BlockCrop();
		cropPoison = new BlockCrop();
		cropEnergy = new BlockCrop();
		cropExchange = new BlockCrop();

		cropMetal = new BlockCrop();
		cropDeath = new BlockCrop();
		cropFlight = new BlockCrop();
		cropDarkness = new BlockCrop();
		cropSoul = new BlockCrop();
		cropHeal = new BlockCrop();
		cropTravel = new BlockCrop();
		cropEldritch = new BlockCrop();
		cropMagic = new BlockCrop();
		cropAura = new BlockCrop();
		cropTaint = new BlockCrop();
		cropSlime = new BlockCrop();
		cropPlant = new BlockCrop();
		cropTree = new BlockCrop();
		cropBeast = new BlockCrop();
		cropFlesh = new BlockCrop();
		cropUndead = new BlockCrop();
		cropMind = new BlockCrop();
		cropSenses = new BlockCrop();
		cropMan = new BlockCrop();
		cropCrop = new BlockCrop();
		cropMine = new BlockCrop();
		cropTool = new BlockCrop();
		cropHarvest = new BlockCrop();
		cropWeapon = new BlockCrop();
		cropArmor = new BlockCrop();
		cropHunger = new BlockCrop();
		cropGreed = new BlockCrop();
		cropCraft = new BlockCrop();
		cropCloth = new BlockCrop();
		cropMechanism = new BlockCrop();
		cropTrap = new BlockCrop();

	}

	private static void registerOthers() {
		registerSeedCrop(seedAir, cropAir, Aspect.AIR, podAir);

	}

	private static void registerCrop(Block block, String name, String key, String material) {
		((CropBase) block).setMaterial(material);
		block.setBlockName(name).setCreativeTab(null);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerSeed(Item item, String name, String key, int color) {
//		((ItemSeed) item).setColor(color);
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + "seed").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerItem(item, name);
	}

	private static void registerSeedCrop(Item seed, Block crop, Aspect aspect, ItemStack drop) {
		registerCrop(crop, aspect.getName() + " Crop", aspect.getName() + "_crop", aspect.getName());
		registerSeed(seed, aspect.getName() + " Seed", aspect.getName() + "_seed", aspect.getColor());
//		RecipeRegistry.addCrop(new SeedCropRecipe(new ItemStack(seed), crop, drop));
	}

}
