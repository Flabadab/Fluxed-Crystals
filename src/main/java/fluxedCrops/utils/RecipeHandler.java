package fluxedCrops.utils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.recipe.RecipeSeedInfuser;
import fluxedCrops.items.FCItems;

public class RecipeHandler {

	public static void init() {
		registerRecipes();
		registerSeedInfuserRecipes();
	}

	private static void registerSeedInfuserRecipes() {
		RecipeRegistry.registerSeedInfuserRecipe(new RecipeSeedInfuser(new ItemStack(Items.iron_ingot), "ironCrop"));
		
	}

	private static void registerRecipes() {

	}

}
