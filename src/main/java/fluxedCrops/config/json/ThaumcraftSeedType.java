package fluxedCrops.config.json;

import thaumcraft.api.aspects.Aspect;
import tterrag.core.common.json.JsonUtils;
import fluxedCrops.FluxedCrops;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.recipe.SeedCropRecipe;

public class ThaumcraftSeedType {

	public String name = "null";
	public String aspect = "aer";

	public void register() {
		try {
			RecipeRegistry.addCrop(new SeedCropRecipe(name, Aspect.getAspect(aspect)));
			FluxedCrops.instance.logger.info("Registering Seed for " + name + " and whose Aspect is" + aspect);
		} catch (IllegalArgumentException e) {
			FluxedCrops.logger.info("Skipping seed type with name {} as its drop was not found.", name);
		}
	}
}
