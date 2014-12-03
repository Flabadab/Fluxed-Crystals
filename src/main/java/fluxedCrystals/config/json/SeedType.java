package fluxedCrystals.config.json;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.SeedCropRecipe;

public class SeedType implements ISeedType {

	public String name = "null";
	public String drop[] = new String[]{};
	public String weightedDrop[] = new String[]{};
    public int weightedDropChance[] = new int[]{};
	public int color = 0xFFFFFF;
	public String ingredient = "minecraft:diamond";
	public int[] dropMin = new int[]{};
	public int[] dropMax = new int[]{};
	public int growthTime = 1200;
	public int tier = 0;
	public int ingredientAmount = 32;
	public int powerPerStage = 2000;

	public void register() {
		try {
			RecipeRegistry.addCrop(new SeedCropRecipe(this));
			FluxedCrystals.logger.info("Registering Seed for " + name + ", that drops " + drop + ", whose color is " + color + " and that is crafted with " + ingredient);
		} catch (IllegalArgumentException e) {
			FluxedCrystals.logger.info("Skipping seed type with name {} as its drop was not found.", name);
		}
	}
}
