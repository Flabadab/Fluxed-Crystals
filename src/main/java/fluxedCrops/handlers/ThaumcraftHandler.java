package fluxedCrops.handlers;

import java.util.ArrayList;

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
import fluxedCrops.blocks.crops.BlockCrop;
import fluxedCrops.items.seeds.ItemSeed;

public class ThaumcraftHandler {

	public static void init() {
		registerAspects();
		registerBlocks();
		registerItems();
		registerOthers();
	}

	public static ArrayList<Aspect> aspects = new ArrayList<Aspect>();

	public static ItemStack[] essentia = new ItemStack[Aspect.aspects.size()];
	public static Block[] essentiaCrops = new BlockCrop[Aspect.aspects.size()];
	public static Item[] essentiaSeeds = new Item[Aspect.aspects.size()];

	private static void registerAspects() {
		aspects.add(Aspect.AIR);
		aspects.add(Aspect.ARMOR);
		aspects.add(Aspect.AURA);
		aspects.add(Aspect.BEAST);
		aspects.add(Aspect.CLOTH);
		aspects.add(Aspect.COLD);
		aspects.add(Aspect.CRAFT);
		aspects.add(Aspect.CROP);
		aspects.add(Aspect.CRYSTAL);
		aspects.add(Aspect.DARKNESS);
		aspects.add(Aspect.DEATH);
		aspects.add(Aspect.EARTH);
		aspects.add(Aspect.ELDRITCH);
		aspects.add(Aspect.ENERGY);
		aspects.add(Aspect.ENTROPY);
		aspects.add(Aspect.EXCHANGE);
		aspects.add(Aspect.FIRE);
		aspects.add(Aspect.FLESH);
		aspects.add(Aspect.FLIGHT);
		aspects.add(Aspect.GREED);
		aspects.add(Aspect.HARVEST);
		aspects.add(Aspect.HEAL);
		aspects.add(Aspect.HUNGER);
		aspects.add(Aspect.LIFE);
		aspects.add(Aspect.LIGHT);
		aspects.add(Aspect.MAGIC);
		aspects.add(Aspect.MAN);
		aspects.add(Aspect.MECHANISM);
		aspects.add(Aspect.METAL);
		aspects.add(Aspect.MIND);
		aspects.add(Aspect.MINE);
		aspects.add(Aspect.MOTION);
		aspects.add(Aspect.ORDER);
		aspects.add(Aspect.PLANT);
		aspects.add(Aspect.POISON);
		aspects.add(Aspect.SENSES);
		aspects.add(Aspect.SLIME);
		aspects.add(Aspect.SOUL);
		aspects.add(Aspect.TAINT);
		aspects.add(Aspect.TOOL);
		aspects.add(Aspect.TRAP);
		aspects.add(Aspect.TRAVEL);
		aspects.add(Aspect.TREE);
		aspects.add(Aspect.UNDEAD);
		aspects.add(Aspect.VOID);
		aspects.add(Aspect.WATER);
		aspects.add(Aspect.WEAPON);
		aspects.add(Aspect.WEATHER);

		for (int i = 0; i < aspects.size(); i++) {
			essentia[i] = ItemApi.getItem("itemManaBean", i);
		}
	}

	private static void registerItems() {
		for (int i = 0; i < essentiaSeeds.length; i++) {
			essentiaSeeds[i] = new ItemSeed(essentiaCrops[i]);
		}

	}

	private static void registerBlocks() {
		for (int i = 0; i < essentiaCrops.length; i++) {
			essentiaCrops[i] = new BlockCrop();
		}
	}

	private static void registerOthers() {
		int i = 0;
		for (Aspect as : aspects) {
			registerCrop(essentiaCrops[i], as.getName() + " Crop", as.getName() + "_crop", CreativeTabs.tabBlock, as.getName(), new ItemStack(essentiaSeeds[i]), essentia[i]);
			registerSeed(essentiaSeeds[i], as.getName() + " Seed", as.getName() + "_seed", as.getColor());
			i++;
		}
	}

	private static void registerCrop(Block block, String name, String key, CreativeTabs tab, String material, ItemStack seed, ItemStack drop) {
		((CropBase) block).setMaterial(material);
		((BlockCrop) block).setSeed(seed);
		((BlockCrop) block).setDrop(drop);
		block.setBlockName(name).setCreativeTab(tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerSeed(Item item, String name, String key, int color) {
		((ItemSeed) item).setColor(color);
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + "seed").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerItem(item, name);
	}

}
