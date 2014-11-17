package fluxedCrops.utils;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

import org.apache.commons.compress.archivers.zip.GeneralPurposeBit;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IAspectContainer;
import thermalfoundation.item.TFItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.recipe.RecipeSeedInfuser;
import fluxedCrops.api.recipe.SeedCropRecipe;
import fluxedCrops.blocks.FCBlocks;
import fluxedCrops.handlers.ThaumcraftHandler;
import fluxedCrops.items.FCItems;

public class RecipeHandler {

	public static void init() {
		registerRecipes();
		registerSeedInfuserRecipes();
		registerCrops();
	}

	private static void registerCrops() {
		RecipeRegistry.addCrop(new SeedCropRecipe(new ItemStack(FCItems.seedIron), FCBlocks.cropIron, TFItems.dustIron));
		RecipeRegistry.addCrop(new SeedCropRecipe(new ItemStack(FCItems.seedGold), FCBlocks.cropGold, TFItems.dustGold));
		RecipeRegistry.addCrop(new SeedCropRecipe(new ItemStack(FCItems.seedCoal), FCBlocks.cropCoal, TFItems.dustCoal));
		RecipeRegistry.addCrop(new SeedCropRecipe(new ItemStack(FCItems.seedCharcoal), FCBlocks.cropCharcoal, TFItems.dustCharcoal));
		RecipeRegistry.addCrop(new SeedCropRecipe(new ItemStack(FCItems.seedCopper), FCBlocks.cropCopper, TFItems.dustCopper));
		RecipeRegistry.addCrop(new SeedCropRecipe(new ItemStack(FCItems.seedRedstone), FCBlocks.cropRedstone, new ItemStack(Items.redstone)));
		RecipeRegistry.addCrop(new SeedCropRecipe(new ItemStack(FCItems.seedDiamond), FCBlocks.cropDiamond, new ItemStack(Items.diamond)));
	}

	private static void registerSeedInfuserRecipes() {
		RecipeRegistry.registerSeedInfuserRecipe(new RecipeSeedInfuser(new ItemStack(Items.iron_ingot), new ItemStack(FCItems.seedIron)));

	}

	private static void registerRecipes() {

	}
}
