package fluxedCrystals.tileEntity;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.RecipeSeedInfuser;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.network.MessageSeedInfuser;
import fluxedCrystals.network.PacketHandler;

/**
 * Created by Jared on 11/2/2014.
 */
public class TileEntitySeedInfuser extends TileEnergyBase implements IInventory {

	public ItemStack[] items;

	@Getter
	private boolean infusing = false;
	@Getter
	private int infused = 0;

	@Getter
	@Setter
	private int recipeIndex;

	public TileEntitySeedInfuser() {
		super(100000);
		items = new ItemStack[3];
	}

	@Override
	public ForgeDirection[] getValidInputs() {
		return new ForgeDirection[] { ForgeDirection.UP, ForgeDirection.DOWN, ForgeDirection.EAST, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.WEST };
	}

	@Override
	public ForgeDirection[] getValidOutputs() {
		return new ForgeDirection[] { ForgeDirection.UNKNOWN };
	}

	public void updateEntity() {
		if (!worldObj.isRemote && worldObj.getTotalWorldTime() % 12 == 0) {
			if (infusing) {
				infuseSeed();
			}
		}
	}

	@Override
	public void closeInventory() {

	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);

		if (itemstack != null) {
			if (itemstack.stackSize <= count) {
				setInventorySlotContents(i, null);
			} else {
				itemstack = itemstack.splitStack(count);

			}
		}

		return itemstack;
	}

	@Override
	public String getInventoryName() {
		return "Seed Infuser";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1) {

		return items[par1];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = getStackInSlot(i);
		setInventorySlotContents(i, item);
		return item;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		items[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	/* NBT */
	@Override
	public void readFromNBT(NBTTagCompound tags) {
		super.readFromNBT(tags);
		readInventoryFromNBT(tags);
		infusing = tags.getBoolean("infusing");
		infused = tags.getInteger("infused");
		setRecipeIndex(tags.getInteger("recipeIndex"));
	}

	public void readInventoryFromNBT(NBTTagCompound tags) {
		NBTTagList nbttaglist = tags.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		for (int iter = 0; iter < nbttaglist.tagCount(); iter++) {
			NBTTagCompound tagList = (NBTTagCompound) nbttaglist.getCompoundTagAt(iter);
			byte slotID = tagList.getByte("Slot");
			if (slotID >= 0 && slotID < items.length) {
				items[slotID] = ItemStack.loadItemStackFromNBT(tagList);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tags) {
		super.writeToNBT(tags);
		writeInventoryToNBT(tags);
		tags.setBoolean("infusing", infusing);
		tags.setInteger("infused", infused);
		tags.setInteger("recipeIndex", getRecipeIndex());
	}

	public void writeInventoryToNBT(NBTTagCompound tags) {
		NBTTagList nbttaglist = new NBTTagList();
		for (int iter = 0; iter < items.length; iter++) {
			if (items[iter] != null) {
				NBTTagCompound tagList = new NBTTagCompound();
				tagList.setByte("Slot", (byte) iter);
				items[iter].writeToNBT(tagList);
				nbttaglist.appendTag(tagList);
			}
		}

		tags.setTag("Items", nbttaglist);
	}

	public boolean infuseSeed() {
		if (getRecipeIndex() >= 0) {
			RecipeSeedInfuser recipe = RecipeRegistry.getSeedRecipes().get(getRecipeIndex());
			if (recipe.matches(getStackInSlot(1)) && getStackInSlot(0).getItem() == FCItems.universalSeed) {
				decrStackSize(1, 1);
				infused++;
				if (infused == RecipeRegistry.getIngredientAmount(recipeIndex)) {
					setInventorySlotContents(0, recipe.getOutput());
					infusing = false;
					infused = 0;
					setRecipeIndex(-1);
					PacketHandler.INSTANCE.sendToDimension(new MessageSeedInfuser(xCoord, yCoord, zCoord, getRecipeIndex()), worldObj.provider.dimensionId);
				}
				return true;
			}
		}
		infused = 0;
		setRecipeIndex(-1);
		infusing = false;
		return false;
	}

	public void setInfusing(boolean infusing) {
		this.infusing = infusing;
		int number = -1;
		setRecipeIndex(number);
		if (getStackInSlot(0) != null && getStackInSlot(1) != null) {
			for (RecipeSeedInfuser recipe : RecipeRegistry.getSeedRecipes()) {
				number++;
				if (recipe.matches(getStackInSlot(1)) && getStackInSlot(0).getItem() == FCItems.universalSeed) {
					setRecipeIndex(number);
					break;
				}
			}
		}
	}
}
