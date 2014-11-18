package fluxedCrops.config.json;

import tterrag.core.common.json.JsonUtils;
import fluxedCrops.FluxedCrops;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.recipe.SeedCropRecipe;

public class SeedType {
	
	public String name = "null";
	public String drop = "minecraft:diamond";
	public int color = 0xFFFFFF;

	public void register() {
		try {
			RecipeRegistry.addCrop(new SeedCropRecipe(name, JsonUtils.parseStringIntoItemStack(drop), color));
		} catch (IllegalArgumentException e) {
			FluxedCrops.logger.info("Skipping seed type with name {} as its drop was not found.", name);
		}
	}
}
