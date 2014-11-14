package fluxedCrops.api.recipe;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class SeedCropRecipe {

	public ItemStack seed;
	public Block crop;
	public ItemStack drop;

	public SeedCropRecipe(ItemStack seed, Block crop, ItemStack drop) {
		this.seed = seed;
		this.crop = crop;
		this.drop = drop;
	}

	public boolean matches(ItemStack seed) {
		return getSeed().isItemEqual(seed);
	}

	public boolean matches(Block crop) {
		return Block.isEqualTo(getCrop(), crop);
	}

	public ItemStack getSeed() {
		return seed;
	}

	public Block getCrop() {
		return crop;
	}

	public ItemStack getDrop() {
		return drop;
	}

}
