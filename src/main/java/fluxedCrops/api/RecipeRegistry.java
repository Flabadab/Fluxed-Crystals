package fluxedCrops.api;

import java.util.ArrayList;

import fluxedCrops.api.recipe.RecipeSeedInfuser;

public class RecipeRegistry {

	public static ArrayList<RecipeSeedInfuser> seedRecipes = new ArrayList<RecipeSeedInfuser>();

	public static ArrayList<RecipeSeedInfuser> getSeedRecipes() {
		return seedRecipes;
	}

	public static void registerSeedInfuserRecipe(RecipeSeedInfuser recipe) {
		getSeedRecipes().add(recipe);
	}

}
