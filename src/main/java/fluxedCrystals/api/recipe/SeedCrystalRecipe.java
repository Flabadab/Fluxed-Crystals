package fluxedCrystals.api.recipe;

import scala.util.parsing.json.JSON;
import tterrag.core.common.json.JsonUtils;
import fluxedCrystals.config.json.SeedType;
import fluxedCrystals.utils.Utils;
import lombok.Getter;
import net.minecraft.item.ItemStack;

@Getter
public class SeedCrystalRecipe {

	private String name;
	private int color;
	private ItemStack ingredient;
	private int dropMin;
	private int dropMax;
	private int growthTime;
	private int tier;
	private int ingredientAmount;
	private int powerPerStage;
	private boolean decorationBlocks;
	private ItemStack weightedDrop;
	private int weightedDropChance;
	private boolean prettyPrettyArmor;

	public SeedCrystalRecipe(SeedType type) {
		this(type.name, type.color, JsonUtils.parseStringIntoItemStack(type.ingredient), type.dropMin, type.dropMax, type.growthTime, type.tier, type.ingredientAmount, type.powerPerStage, type.decorationBlocks, JsonUtils.parseStringIntoItemStack(type.weightedDrop), type.weightedDropChance, type.prettyPrettyArmor);
	}

	public SeedCrystalRecipe(String name, int color, ItemStack ingredient, int dropMin, int dropMax, int growthTime, int tier, int ingredientAmount, int powerPerStage, boolean decorationBlock, ItemStack weightedDrop, int weightedDropChance, boolean prettyPrettyArmor) {
		this.name = name;
		this.color = color;
		this.ingredient = ingredient;
		this.dropMin = dropMin;
		this.dropMax = dropMax;
		this.growthTime = growthTime;
		this.tier = tier;
		this.ingredientAmount = ingredientAmount;
		this.powerPerStage = powerPerStage;
		this.decorationBlocks = decorationBlock;
		this.weightedDrop = weightedDrop;
		this.weightedDropChance = weightedDropChance;
		this.prettyPrettyArmor = prettyPrettyArmor;
	}
}
