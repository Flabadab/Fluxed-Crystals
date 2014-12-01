package fluxedCrops.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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
	
	public static SeedCropRecipe getSeedRecipe(int itemDamage) {
		if (rangeCheck(itemDamage)) {
			return crops.get(itemDamage);
		}
		return null;
	}
	
	public static int getNumSeedRecipes() {
		return crops.size();
	}
	
	public static int getNumInfuserRecipes() {
		return seedRecipes.size();
	}

	private static boolean rangeCheck(int idx) {
		return idx >= 0 && idx < crops.size();
	}

	public static int getColor(int itemDamage) {
		if (rangeCheck(itemDamage))
			return crops.get(itemDamage).getColor();
		return 0;
	}

	public static ItemStack getDrop(int itemDamage) {
		if (rangeCheck(itemDamage)) {
			ItemStack stack = crops.get(itemDamage).getDrop().copy();
			stack.stackSize = getDropAmount(itemDamage);
			return stack;
		}
		return null;
	}

	public static ItemStack getIngredient(int itemDamage) {
		if (rangeCheck(itemDamage))
			return crops.get(itemDamage).getIngredient();
		return null;
	}

	private static final Random rand = new Random();
	private static int getDropAmount(int itemDamage) {
		SeedCropRecipe r = getSeedRecipe(itemDamage);
		int min = r.getDropMin();
		int max = r.getDropMax();
		return rand.nextInt(max - min + 1) + min;
	}

	public static int getGrowthTime(int itemDamage) {
		if (rangeCheck(itemDamage))
			return crops.get(itemDamage).getGrowthTime();
		return 0;
	}

	public static String getName(int itemDamage) {
		if (rangeCheck(itemDamage))
			return crops.get(itemDamage).getName();
		return null;
	}

	public static int getTier(int itemDamage) {
		if (rangeCheck(itemDamage))
			return crops.get(itemDamage).getTier();
		return 0;
	}

	public static int getIngredientAmount(int itemDamage) {
		if (rangeCheck(itemDamage))
			return crops.get(itemDamage).getIngredientAmount();
		return 0;
	}

	public static int getPowerPerStage(int itemDamage) {
		if (rangeCheck(itemDamage))
			return crops.get(itemDamage).getPowerPerStage();
		return 0;
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
