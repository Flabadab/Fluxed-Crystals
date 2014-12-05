package fluxedCrystals.config.json;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.SeedCropRecipe;

public class SeedType implements ISeedType {

	public String name = "null";
	public String drop = "minecraft:diamond";
	public int color = 0xFFFFFF;
	public String ingredient = "minecraft:diamond";
	public int dropMin =1;
	public int dropMax = 1;
	public int growthTime = 1200;
	public int tier = 0;
	public int ingredientAmount = 32;
	public int powerPerStage = 2000;
	public boolean decorationBlocks = true;
	public String weightedDrop = "minecraft:diamond";
	public int weightedDropChance = 0;
	

	public void register() {
		try {
			RecipeRegistry.addCrop(new SeedCropRecipe(this));
			FluxedCrystals.logger.info("Registering Seed for " + name + ", that drops " + drop + ", whose color is " + color + " and that is crafted with " + ingredient);
		} catch (IllegalArgumentException e) {
			FluxedCrystals.logger.info("Skipping seed type with name {} as its drop was not found.", name);
		}
	}
}
