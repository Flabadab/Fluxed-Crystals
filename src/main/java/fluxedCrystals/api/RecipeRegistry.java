package fluxedCrystals.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;

import com.google.common.collect.ImmutableList;

import fluxedCrystals.api.recipe.RecipeGemCutter;
import fluxedCrystals.api.recipe.RecipeGemRefiner;
import fluxedCrystals.api.recipe.RecipeSeedInfuser;
import fluxedCrystals.api.recipe.SeedCrystalRecipe;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecipeRegistry {

	private static List<RecipeSeedInfuser> seedRecipes = new ArrayList<RecipeSeedInfuser>();
	private static List<SeedCrystalRecipe> crops = new ArrayList<SeedCrystalRecipe>();
	private static List<RecipeGemRefiner> gemsRef = new ArrayList<RecipeGemRefiner>();
	private static List<RecipeGemCutter> gemsCut = new ArrayList<RecipeGemCutter>();

	public static List<RecipeSeedInfuser> getSeedRecipes() {
		return seedRecipes;
	}

	public static List<RecipeGemRefiner> getGemRefinerRecipes() {
		return gemsRef;
	}

	public static List<RecipeGemCutter> getGemCutterRecipes() {
		return gemsCut;
	}

	public static void registerSeedInfuserRecipe(RecipeSeedInfuser recipe) {
		if (!seedRecipes.contains(recipe))
			seedRecipes.add(recipe);

	}

	public static void addCrop(SeedCrystalRecipe type) {
		crops.add(type);
	}

	public static void registerGemRefinerRecipe(RecipeGemRefiner type) {
		gemsRef.add(type);
	}

	public static void registerGemCutterRecipe(RecipeGemCutter type) {
		gemsCut.add(type);
	}

	public static void addCrops(Collection<SeedCrystalRecipe> types) {
		for (SeedCrystalRecipe r : types) {
			addCrop(r);
		}
	}

	public static List<SeedCrystalRecipe> getSeedCropRecipes() {
		return ImmutableList.copyOf(crops);
	}

	public static SeedCrystalRecipe getSeedRecipe(int itemDamage) {
		if (rangeCheck(itemDamage)) {
			return crops.get(itemDamage);
		}
		return null;
	}

	public static boolean hasPrettyPrettyArmor(int itemDamage) {
		if (rangeCheck(itemDamage))
			return crops.get(itemDamage).isPrettyPrettyArmor();
		return false;

	}

	public static int getSeedReturn(int itemDamage) {
		if (rangeCheck(itemDamage)) {
			return crops.get(itemDamage).getSeedReturn();
		}
		return 1;
	}

	public static int getEntityID(int itemDamage) {
		if (rangeCheck(itemDamage)) {
			return crops.get(itemDamage).getEntityID();
		}
		return 0;
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

	public static boolean getHasDecorationBlocks(int itemDamage) {
		if (rangeCheck(itemDamage)) {
			return crops.get(itemDamage).isDecorationBlocks();
		}
		return true;
	}

	public static boolean getIsSharp(int itemDamage) {
		if (rangeCheck(itemDamage)) {
			return crops.get(itemDamage).isSharp();
		}
		return true;
	}

	public static Aspect getAspectNeeded(int itemDamage) {
		if (rangeCheck(itemDamage)) {
			return crops.get(itemDamage).getAspectNeeded();
		}
		return null;
	}

	public static int getAspectNeededAmount(int itemDamage) {
		if (rangeCheck(itemDamage)) {
			return crops.get(itemDamage).getAspectNeededAmount();
		}
		return 32;
	}

	public static ItemStack getWeightedDrop(int itemDamage) {
		if (rangeCheck(itemDamage)) {
			return crops.get(itemDamage).getWeightedDrop();
		}
		return null;
	}

	public static int getWeightedDropChance(int itemDamage) {
		if (rangeCheck(itemDamage)) {
			return crops.get(itemDamage).getWeightedDropChance();
		}
		return 0;
	}

	public static String getLore(int itemDamage) {
		if (rangeCheck(itemDamage)) {
			return crops.get(itemDamage).getLore();
		}
		return "null";
	}

	public static ItemStack getIngredient(int itemDamage) {
		if (rangeCheck(itemDamage))
			return crops.get(itemDamage).getIngredient();
		return null;
	}

	private static final Random rand = new Random();

	public static int getDropAmount(int itemDamage) {
		ArrayList<Integer> amount = new ArrayList<Integer>();
		SeedCrystalRecipe r = getSeedRecipe(itemDamage);
		int min = r.getDropMin();
		int max = r.getDropMax();
		return rand.nextInt(max - min + 1) + min;
	}

	public static int getDropAmount(int dropMin, int dropMax) {
		int min = dropMin;
		int max = dropMax;
		return rand.nextInt(max - min + 1) + min;
	}

	public static int getGrowthTime(int itemDamage) {
		if (rangeCheck(itemDamage))
			return crops.get(itemDamage).getGrowthTime();
		return 1;
	}

	public static String getName(int itemDamage) {
		if (rangeCheck(itemDamage))
			return crops.get(itemDamage).getName();
		return "name";
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

	public static int getRefinerAmount(int itemDamage) {
		if (rangeCheck(itemDamage)) {
			return crops.get(itemDamage).getRefinerAmount();

		}
		return 1;
	}

	public static int getPowerPerStage(int itemDamage) {
		if (rangeCheck(itemDamage))
			return crops.get(itemDamage).getPowerPerStage();
		return 1;
	}

	public static void reset() {
		String caller = Thread.currentThread().getStackTrace()[2].getClassName();
		if (caller.contains("fluxedCrystals.config")) {
			crops.clear();
			seedRecipes.clear();
			gemsCut.clear();
			gemsRef.clear();
		} else {
			throw new RuntimeException(caller + " tried to clear the FluxedCrystals recipe registry. They can't do that!");
		}
	}
}
