package fluxedCrops.api.recipe;

import lombok.Getter;
import net.minecraft.item.ItemStack;

@Getter
public class SeedCropRecipe {

	private String name;
	private ItemStack drop;
	private int color;

	public SeedCropRecipe(String name, ItemStack drop, int color) {
		this.name = name;
		this.drop = drop;
		this.color = color;
	}
}
