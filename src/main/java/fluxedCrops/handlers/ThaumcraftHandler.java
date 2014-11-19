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
		registerOthers();
	}

	private static void registerOthers() {
		registerSeedCrop("Aer", Aspect.AIR);
		registerSeedCrop("Tutamen", Aspect.ARMOR);
		registerSeedCrop("Aurum", Aspect.AURA);
		registerSeedCrop("Beastia", Aspect.BEAST);
		registerSeedCrop("Pannus", Aspect.CLOTH);
		registerSeedCrop("Gelum", Aspect.COLD);
		registerSeedCrop("Fabrico", Aspect.CRAFT);
		registerSeedCrop("Messis", Aspect.CROP);
		registerSeedCrop("Vitreus", Aspect.CRYSTAL);
		registerSeedCrop("Tenebrae", Aspect.DARKNESS);
		registerSeedCrop("Mortuus", Aspect.DEATH);
		registerSeedCrop("Terra", Aspect.EARTH);
		registerSeedCrop("Alienis", Aspect.ELDRITCH);
		registerSeedCrop("Potentia", Aspect.ENERGY);
		registerSeedCrop("Entropy", Aspect.ENTROPY);
		registerSeedCrop("Permutatio", Aspect.EXCHANGE);
		registerSeedCrop("Ignis", Aspect.FIRE);
		registerSeedCrop("Corpus", Aspect.FLESH);
		registerSeedCrop("Volatus", Aspect.FLIGHT);
		registerSeedCrop("Lucrum", Aspect.GREED);
		registerSeedCrop("Meto", Aspect.HARVEST);
		registerSeedCrop("Sano", Aspect.HEAL);
		registerSeedCrop("Fames", Aspect.HUNGER);
		registerSeedCrop("Victus", Aspect.LIFE);
		registerSeedCrop("Lux", Aspect.LIGHT);
		registerSeedCrop("Praecantatio", Aspect.MAGIC);
		registerSeedCrop("Humanus", Aspect.MAN);
		registerSeedCrop("Machina", Aspect.MECHANISM);
		registerSeedCrop("Metallum", Aspect.METAL);
		registerSeedCrop("Cognito", Aspect.MIND);
		registerSeedCrop("Perfodio", Aspect.MINE);
		registerSeedCrop("Motus", Aspect.MOTION);
		registerSeedCrop("Ordo", Aspect.ORDER);
		registerSeedCrop("Herba", Aspect.PLANT);
		registerSeedCrop("Venenum", Aspect.POISON);
		registerSeedCrop("Sensus", Aspect.SENSES);
		registerSeedCrop("Limus", Aspect.SLIME);
		registerSeedCrop("Spiritus", Aspect.SOUL);
		registerSeedCrop("Vitium", Aspect.TAINT);
		registerSeedCrop("Instrumentum", Aspect.TOOL);
		registerSeedCrop("Vinculum", Aspect.TRAP);
		registerSeedCrop("Iter", Aspect.TRAVEL);
		registerSeedCrop("Arbor", Aspect.TREE);
		registerSeedCrop("Exanimis", Aspect.UNDEAD);
		registerSeedCrop("vacuos", Aspect.VOID);
		registerSeedCrop("Aqua", Aspect.WATER);
		registerSeedCrop("Telum", Aspect.WEAPON);
		registerSeedCrop("Tempestas", Aspect.WEATHER);

	}

	private static void registerSeedCrop(String name, Aspect aspect) {
		ItemStack drop = ItemApi.getItem("itemCrystalEssence", 0).copy();
		if (drop.getItem() instanceof IEssentiaContainerItem) {
			((IEssentiaContainerItem) drop.getItem()).setAspects(drop, new AspectList().add(aspect, 1));
		}
		RecipeRegistry.addCrop(new SeedCropRecipe(name, drop, aspect.getColor(), drop));
		FluxedCrops.instance.logger.info("Registering Crop Recipe for " + aspect.getName());

	}

}
