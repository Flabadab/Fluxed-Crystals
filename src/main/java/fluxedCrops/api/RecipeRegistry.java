package fluxedCrops.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import thaumcraft.api.aspects.Aspect;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import fluxedCrops.api.recipe.RecipeSeedInfuser;
import fluxedCrops.api.recipe.SeedCropRecipe;

public class RecipeRegistry {

	public static ArrayList<RecipeSeedInfuser> seedRecipes = new ArrayList<RecipeSeedInfuser>();

	public static ArrayList<SeedCropRecipe> crops = new ArrayList<SeedCropRecipe>();

	public static ArrayList<RecipeSeedInfuser> getSeedRecipes() {
		return seedRecipes;

	}

	public static void registerSeedInfuserRecipe(RecipeSeedInfuser recipe) {
		getSeedRecipes().add(recipe);
	}

	public static void addCrop(SeedCropRecipe recipe) {
		crops.add(recipe);
	}

	public static ArrayList<SeedCropRecipe> getSeedCropRecipes() {
		return crops;
	}

}
