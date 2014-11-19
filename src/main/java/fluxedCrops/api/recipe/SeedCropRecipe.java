package fluxedCrops.api.recipe;

import lombok.Getter;
import net.minecraft.item.ItemStack;

@Getter
public class SeedCropRecipe {

	private String name;
	private ItemStack drop;
	private int color;
	private ItemStack recipeIngredient;

	public SeedCropRecipe(String name, ItemStack drop, int color, ItemStack ingredient) {
		this.name = name;
		this.drop = drop;
		this.color = color;
		this.recipeIngredient= ingredient;
	}
}
