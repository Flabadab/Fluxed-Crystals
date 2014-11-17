package fluxedCrops.tileEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import fluxedCrops.api.RecipeRegistry;

public class TileEntityCrop extends TileEntity {

	private int idx = 0;

	public TileEntityCrop() {

	}
	
	public ItemStack getDrop() {
		return RecipeRegistry.getDrop(idx);
	}
	
	public int getIndex() {
		return idx;
	}

	public void init(int itemDamage) {
		this.idx = itemDamage;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("index", idx);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.idx = tag.getInteger("index");
	}
}
