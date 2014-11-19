package fluxedCrops.handlers;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.FluxedCrops;
import fluxedCrops.ModProps;
import fluxedCrops.api.CropBase;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.recipe.RecipeSeedInfuser;
import fluxedCrops.api.recipe.SeedCropRecipe;
import fluxedCrops.blocks.crops.BlockCrop;
import fluxedCrops.items.FCItems;
import fluxedCrops.items.seeds.ItemSeed;

public class ThaumcraftHandler {

	public static void init() {
		registerAspects();
		registerOthers();

	}

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

	}

	private static void registerOthers() {
		registerSeedCrop(seedAir, Aspect.AIR);
		registerSeedCrop(seedArmor, Aspect.ARMOR);
		registerSeedCrop(seedAura, Aspect.AURA);
		registerSeedCrop(seedBeast, Aspect.BEAST);
		registerSeedCrop(seedCloth, Aspect.CLOTH);
		registerSeedCrop(seedCold, Aspect.COLD);
		registerSeedCrop(seedCraft, Aspect.CRAFT);
		registerSeedCrop(seedCrop, Aspect.CROP);
		registerSeedCrop(seedCrystal, Aspect.CRYSTAL);
		registerSeedCrop(seedDarkness, Aspect.DARKNESS);
		registerSeedCrop(seedDeath, Aspect.DEATH);
		registerSeedCrop(seedEarth, Aspect.EARTH);
		registerSeedCrop(seedEldritch, Aspect.ELDRITCH);
		registerSeedCrop(seedEnergy, Aspect.ENERGY);
		registerSeedCrop(seedEntropy, Aspect.ENTROPY);
		registerSeedCrop(seedExchange, Aspect.EXCHANGE);
		registerSeedCrop(seedFire, Aspect.FIRE);
		registerSeedCrop(seedFlesh, Aspect.FLESH);
		registerSeedCrop(seedFlight, Aspect.FLIGHT);
		registerSeedCrop(seedGreed, Aspect.GREED);
		registerSeedCrop(seedHarvest, Aspect.HARVEST);
		registerSeedCrop(seedHeal, Aspect.HEAL);
		registerSeedCrop(seedHunger, Aspect.HUNGER);
		registerSeedCrop(seedLife, Aspect.LIFE);
		registerSeedCrop(seedLight, Aspect.LIGHT);
		registerSeedCrop(seedMagic, Aspect.MAGIC);
		registerSeedCrop(seedMan, Aspect.MAN);
		registerSeedCrop(seedMechanism, Aspect.MECHANISM);
		registerSeedCrop(seedMetal, Aspect.METAL);
		registerSeedCrop(seedMind, Aspect.MIND);
		registerSeedCrop(seedMine, Aspect.MINE);
		registerSeedCrop(seedMotion, Aspect.MOTION);
		registerSeedCrop(seedOrder, Aspect.ORDER);
		registerSeedCrop(seedPlant, Aspect.PLANT);
		registerSeedCrop(seedPoison, Aspect.POISON);
		registerSeedCrop(seedSenses, Aspect.SENSES);
		registerSeedCrop(seedSlime, Aspect.SLIME);
		registerSeedCrop(seedSoul, Aspect.SOUL);
		registerSeedCrop(seedTaint, Aspect.TAINT);
		registerSeedCrop(seedTool, Aspect.TOOL);
		registerSeedCrop(seedTrap, Aspect.TRAP);
		registerSeedCrop(seedTravel, Aspect.TRAVEL);
		registerSeedCrop(seedTree, Aspect.TREE);
		registerSeedCrop(seedUndead, Aspect.UNDEAD);
		registerSeedCrop(seedVoid, Aspect.VOID);
		registerSeedCrop(seedWater, Aspect.WATER);
		registerSeedCrop(seedWeapon, Aspect.WEAPON);
		registerSeedCrop(seedWeather, Aspect.WEATHER);

	}

	private static void registerCrop(Block block, String name, String key, String material) {
		((CropBase) block).setMaterial(material);
		block.setBlockName(name).setCreativeTab(null);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerSeed(Item item, String name, String key, int color) {
		// ((ItemSeed) item).setColor(color);
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + "seed").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerItem(item, name);
	}

	private static void registerSeedCrop(Item seed, Aspect aspect) {
		ItemStack drop = ItemApi.getItem("itemCrystalEssence", 0).copy();
		if (drop.getItem() instanceof IEssentiaContainerItem) {
			((IEssentiaContainerItem) drop.getItem()).setAspects(drop, new AspectList().add(aspect, 1));
		}
		RecipeRegistry.addCrop(new SeedCropRecipe(new ItemStack(seed).getDisplayName(), drop, aspect.getColor(), drop));
		RecipeRegistry.registerSeedInfuserRecipe(new RecipeSeedInfuser(drop, new ItemStack(seed)));
		FluxedCrops.instance.logger.info("Registering Crop Recipe for " + aspect.getName());
		
	}

}
