package fluxedCrops.blocks.crops;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import fluxedCrops.api.CropBase;
import fluxedCrops.items.FCItems;

public class BlockCrop extends CropBase {

	private Item seed;
	private Item drop;

	public BlockCrop(Item seed, Item drop) {
		this.seed = seed;
		this.drop = drop;
	}

	public Item getSeed() {
		return seed;
	}

	public Item getDrop() {
		return drop;
	}

}
