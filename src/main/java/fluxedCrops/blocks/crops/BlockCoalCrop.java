package fluxedCrops.blocks.crops;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import fluxedCrops.api.CropBase;
import fluxedCrops.items.FCItems;

public class BlockCoalCrop extends CropBase {

	public BlockCoalCrop() {
	}

	protected Item getSeed() {
		return FCItems.charcoalSeed;
	}

	protected Item getDrop() {
		return FCItems.charcoalShard;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this.getSeed()));
		System.out.println(metadata);
		if (metadata >= 7)
			ret.add(new ItemStack(this.getDrop(), 1, 0));

		return ret;
	}

}
