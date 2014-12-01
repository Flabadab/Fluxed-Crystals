package fluxedCrops.api.recipe;

import lombok.Getter;
import net.minecraft.item.ItemStack;

@Getter
public class SeedCropRecipe {

	private String name;
	private ItemStack drop;
	private int color;
	private ItemStack ingredient;
	private int dropMin;
	private int dropMax;
	private int growthTime;
	private int tier;
	private int ingredientAmount;
	private int powerPerStage;
	
	public SeedCropRecipe(String name, ItemStack drop, int color, ItemStack ingredient, int dropMin, int dropMax, int growthTime, int tier, int ingredientAmount, int powerPerStage) {
		this.name = name;
		this.drop = drop;
		this.color = color;
		this.ingredient = ingredient;
		this.dropMin = dropMin;
		this.dropMax = dropMax;
		this.growthTime = growthTime;
		this.tier = tier;
		this.ingredientAmount = ingredientAmount;
		this.powerPerStage = powerPerStage;
	}

}
