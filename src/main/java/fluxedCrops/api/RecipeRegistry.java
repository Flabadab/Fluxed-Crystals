package fluxedCrops.api;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.item.ItemStack;

import com.google.common.collect.ImmutableList;

import fluxedCrops.api.recipe.RecipeSeedInfuser;
import fluxedCrops.api.recipe.SeedCropRecipe;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecipeRegistry {

	public static List<RecipeSeedInfuser> seedRecipes = new ArrayList<RecipeSeedInfuser>();

	public static List<SeedCropRecipe> crops = new ArrayList<SeedCropRecipe>();

	public static List<RecipeSeedInfuser> getSeedRecipes() {
		return ImmutableList.copyOf(seedRecipes);
	}

	public static void registerSeedInfuserRecipe(RecipeSeedInfuser recipe) {
		seedRecipes.add(recipe);
	}

	public static void addCrop(SeedCropRecipe type) {
		crops.add(type);
	}

	public static List<SeedCropRecipe> getSeedCropRecipes() {
		return ImmutableList.copyOf(crops);
	}

	public static int getColor(int itemDamage) {
		return crops.get(itemDamage).getColor();
	}
	
	public static ItemStack getDrop(int itemDamage) {
		return crops.get(itemDamage).getDrop().copy();
	}

	public static String getName(int itemDamage) {
		return crops.get(itemDamage).getName();
	}
}
