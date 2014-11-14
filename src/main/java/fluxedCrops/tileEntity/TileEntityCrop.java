package fluxedCrops.tileEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCrop extends TileEntity {

	private ItemStack seed;
	private ItemStack drop;

	public TileEntityCrop() {

	}

	public TileEntityCrop(ItemStack seed, ItemStack drop) {
		this.seed = seed;
		this.drop = drop;

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
