package fluxedCrops.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.item.ItemStack;

import com.google.common.collect.ImmutableList;

import fluxedCrops.api.recipe.RecipeSeedInfuser;
import fluxedCrops.api.recipe.SeedCropRecipe;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecipeRegistry {

	private static List<RecipeSeedInfuser> seedRecipes = new ArrayList<RecipeSeedInfuser>();
	private static List<SeedCropRecipe> crops = new ArrayList<SeedCropRecipe>();

	public static List<RecipeSeedInfuser> getSeedRecipes() {
		return ImmutableList.copyOf(seedRecipes);
	}

	public static void registerSeedInfuserRecipe(RecipeSeedInfuser recipe) {
		seedRecipes.add(recipe);
	}

	public static void addCrop(SeedCropRecipe type) {
		crops.add(type);
	}

	public static void addCrops(Collection<SeedCropRecipe> types) {
		for (SeedCropRecipe r : types) {
			addCrop(r);
		}
	}

	public static List<SeedCropRecipe> getSeedCropRecipes() {
		return ImmutableList.copyOf(crops);
	}

	public static int getColor(int itemDamage) {
		if (itemDamage >= 0 && itemDamage < crops.size())
			return crops.get(itemDamage).getColor();
		return 0;
	}

	public static ItemStack getDrop(int itemDamage) {
		if (itemDamage >= 0 && itemDamage < crops.size())
			return crops.get(itemDamage).getDrop().copy();
		return null;
	}

	public static int getDropAmount(int itemDamage) {
		if (itemDamage >= 0 && itemDamage < crops.size())
			return crops.get(itemDamage).getDropAmount();
		return 1;
	}

	public static int getGrowthTime(int itemDamage) {
		if (itemDamage >= 0 && itemDamage < crops.size())
			return crops.get(itemDamage).getGrowthTime();
		return 0;
	}

	public static String getName(int itemDamage) {
		if (itemDamage >= 0 && itemDamage < crops.size())
			return crops.get(itemDamage).getName();
		return null;
	}

	public static void reset() {
		String caller = Thread.currentThread().getStackTrace()[2].getClassName();
		if (caller.contains("fluxedCrops.config")) {
			crops.clear();
			seedRecipes.clear();
		} else {
			throw new RuntimeException(caller + " tried to clear the FluxedCrops recipe registry. They can't do that!");
		}
	}
}
