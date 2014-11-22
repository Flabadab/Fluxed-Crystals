package fluxedCrops.config.json;

import thaumcraft.api.aspects.Aspect;
import fluxedCrops.FluxedCrops;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.recipe.SeedCropRecipe;

public class ThaumcraftSeedType implements ISeedType {

	public String name = "null";
	public String aspect = "aer";
	public void register() {
		try {
		} catch (IllegalArgumentException e) {
			FluxedCrops.logger.info("Skipping seed type with name {} as its drop was not found.", name);
		}
	}
}
