package fluxedCrystals.api.recipe;

import lombok.Getter;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import tterrag.core.common.json.JsonUtils;
import fluxedCrystals.config.json.SeedType;

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
	private int refinerAmount;
	private String lore;
	private boolean sharp;
	private Aspect aspectNeeded;
	private int aspectNeededAmount;
	private int seedReturn;
	private int dimensionWhitelist;
	private int entityID;

	public SeedCrystalRecipe(SeedType type) {
		this(type.name, type.color, JsonUtils.parseStringIntoItemStack(type.ingredient), type.dropMin, type.dropMax, type.growthTime, type.tier, type.ingredientAmount, type.powerPerStage, type.decorationBlocks, JsonUtils.parseStringIntoItemStack(type.weightedDrop), type.weightedDropChance, type.prettyPrettyArmor, type.refinerAmount, type.lore, type.sharp, Aspect.getAspect(type.aspectNeeded), type.aspectNeededAmount, type.seedReturn, type.dimensionWhitelist, type.entityID);
	}

	public SeedCrystalRecipe(String name, int color, ItemStack ingredient, int dropMin, int dropMax, int growthTime, int tier, int ingredientAmount, int powerPerStage, boolean decorationBlock, ItemStack weightedDrop, int weightedDropChance, boolean prettyPrettyArmor, int refinerAmount, String lore, boolean sharp, Aspect aspectNeeded, int aspectNeededAmount, int seedReturn, int dimensionWhitelist, int entityID) {
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
		this.refinerAmount = refinerAmount;
		this.lore = lore;
		this.sharp = sharp;
		this.aspectNeeded = aspectNeeded;
		this.aspectNeededAmount = aspectNeededAmount;
		this.seedReturn = seedReturn;
		this.dimensionWhitelist = dimensionWhitelist;
		this.entityID = entityID;
	}
}
