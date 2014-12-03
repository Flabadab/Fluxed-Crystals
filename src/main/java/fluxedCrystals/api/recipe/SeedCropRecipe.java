package fluxedCrystals.api.recipe;

import tterrag.core.common.json.JsonUtils;
import fluxedCrystals.config.json.SeedType;
import fluxedCrystals.utils.Utils;
import lombok.Getter;
import net.minecraft.item.ItemStack;

@Getter
public class SeedCropRecipe {

    private String name;
    private ItemStack[] drop;
    public ItemStack[] weightedDrop[];
    public int weightedDropChance[];
    private int color;
    private ItemStack ingredient;
    private int[] dropMin;
    private int[] dropMax;
    private int growthTime;
    private int tier;
    private int ingredientAmount;
    private int powerPerStage;

    public SeedCropRecipe(SeedType type) {
        this(type.name, Utils.parseStringArrayIntoItemStackArray(type.drop), Utils.parseStringArrayIntoItemStackArray(type.weightedDrop), type.weightedDropChance, type.color, JsonUtils.parseStringIntoItemStack(type.ingredient), type.dropMin, type.dropMax, type.growthTime, type.tier, type.ingredientAmount, type.powerPerStage);
    }

    public SeedCropRecipe(String name, ItemStack[] drop, ItemStack[] weightedDrop, int[] weightedDropChance, int color, ItemStack ingredient, int dropMin[], int dropMax[], int growthTime, int tier, int ingredientAmount, int powerPerStage) {
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
