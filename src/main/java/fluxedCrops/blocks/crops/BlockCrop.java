package fluxedCrops.blocks.crops;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import fluxedCrops.api.CropBase;
import fluxedCrops.items.FCItems;

public class BlockCrop extends CropBase {

	protected ItemStack seed;
	protected ItemStack drop;

	public BlockCrop() {
	}

	public ItemStack getSeed() {
		return seed;
	}

	public ItemStack getDrop() {
		return drop;
	}

	public void setSeed(ItemStack seed) {
		this.seed = seed;
	}

	public void setDrop(ItemStack drop) {
		this.drop = drop;
	}
}
