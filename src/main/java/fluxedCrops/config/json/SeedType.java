package fluxedCrops.config.json;

import thaumcraft.api.aspects.Aspect;
import tterrag.core.common.json.JsonUtils;
import fluxedCrops.FluxedCrops;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.recipe.SeedCropRecipe;
import fluxedCrops.utils.ModUtils;

public class SeedType implements ISeedType {

	public String name = "null";
	public String drop = "minecraft:diamond";
	public int color = 0xFFFFFF;
	public String ingredient = "minecraft:diamond";
	public String aspect;
	public int dropAmount = 1;
	public int growthTime = 1200;

	public void register() {
		try {
			if (aspect == null) {
				RecipeRegistry.addCrop(new SeedCropRecipe(name, JsonUtils.parseStringIntoItemStack(drop), color, JsonUtils.parseStringIntoItemStack(ingredient), dropAmount, growthTime));
				FluxedCrops.logger.info("Registering Seed for " + name + ", that drops " + drop + ", whose color is " + color + " and that is crafted with " + ingredient);
			} else if (ModUtils.isModLoaded("Thaumcraft")) {
				RecipeRegistry.addCrop(new SeedCropRecipe(name, Aspect.getAspect(aspect), dropAmount, growthTime));
				FluxedCrops.logger.info("Registering Seed for " + name + " and whose Aspect is " + aspect);

			}
		} catch (IllegalArgumentException e) {
			FluxedCrops.logger.info("Skipping seed type with name {} as its drop was not found.", name);
		}
	}
}
