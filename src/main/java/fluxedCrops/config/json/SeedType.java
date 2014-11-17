package fluxedCrops.config.json;

import tterrag.core.common.json.JsonUtils;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.recipe.SeedCropRecipe;

public class SeedType {
	
	public String name = "null";
	public String drop = "minecraft:diamond";
	public int color = 0xFFFFFF;

	public void register() {
		RecipeRegistry.addCrop(new SeedCropRecipe(name, JsonUtils.parseStringIntoItemStack(drop), color));
	}
}
