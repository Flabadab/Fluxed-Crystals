package fluxedCrops.tileEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCrop extends TileEntity {

	private ItemStack seed;
	private ItemStack drop;

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
