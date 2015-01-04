package fluxedCrystals.tileEntity;

import java.util.ArrayList;

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
import fluxedCrystals.api.recipe.RecipeGemCutter;
import fluxedCrystals.api.recipe.RecipeGemRefiner;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.network.MessageGemCutter;
import fluxedCrystals.network.MessageGemRefiner;
import fluxedCrystals.network.PacketHandler;

/**
 * Created by Jared on 11/2/2014.
 */
public class TileEntityGemCutter extends TileEnergyBase implements IInventory {

	public ItemStack[] items;

	@Getter
	private boolean cutting = false;
	@Getter
	private int cut = 0;

	@Getter
	@Setter
	private int recipeIndex;

	public TileEntityGemCutter() {
		super(10000);
		items = new ItemStack[5];

	}

	public void updateEntity() {
		if (getStackInSlot(0) != null && !cutting) {
			PacketHandler.INSTANCE.sendToServer(new MessageGemCutter(xCoord, yCoord, zCoord));
		}
		if(getStackInSlot(1)!=null){
			if (cutting && worldObj.getWorldTime() % getSpeed() == 0 && storage.getEnergyStored() >= getEffeciency() && getStackInSlot(1).stackSize<getStackInSlot(1).getMaxStackSize()) {
				refine();
			}
		}else{
			if (cutting && worldObj.getWorldTime() % getSpeed() == 0 && storage.getEnergyStored() >= getEffeciency()) {
				refine();
			}
		}
		
	}

	public boolean isUpgradeActive(ItemStack stack) {
		return (getUpgradeSlotOne() != null && getUpgradeSlotTwo().isItemEqual(stack)) || (getUpgradeSlotThree() != null && getUpgradeSlotTwo().isItemEqual(stack)) || (getUpgradeSlotThree() != null && getUpgradeSlotThree().isItemEqual(stack));
	}

	public ArrayList<ItemStack> getUpgrades() {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(getUpgradeSlotOne());
		list.add(getUpgradeSlotTwo());
		list.add(getUpgradeSlotThree());
		return list;
	}

	public int getSpeed() {
		int speed = 100;
		for (ItemStack item : getUpgrades()) {
			if (item != null) {
				if (item.isItemEqual(new ItemStack(FCItems.upgradeSpeed))) {
					speed -= 20;
				}
			}
		}
		return speed;
	}

	public int getEffeciency() {
		int eff = 250;
		for (ItemStack item : getUpgrades()) {
			if (item != null) {
				if (item.isItemEqual(new ItemStack(FCItems.upgradeSpeed))) {
					eff += 15;
				}

			}
		}
		for (ItemStack item : getUpgrades()) {
			if (item != null) {
				if (item.isItemEqual(new ItemStack(FCItems.upgradeSpeed))) {
					eff += 15;
				}

			}
		}
		for (ItemStack item : getUpgrades()) {
			if (item != null) {
				if (item.isItemEqual(new ItemStack(FCItems.upgradeEffeciency))) {
					eff -= 25;
				}
			}
		}

		if (eff == 0) {
			eff = 1;
		}
		return eff;
	}

	public ItemStack getUpgradeSlotOne() {
		return getStackInSlot(2);
	}

	public ItemStack getUpgradeSlotTwo() {
		return getStackInSlot(3);
	}

	public ItemStack getUpgradeSlotThree() {
		return getStackInSlot(4);
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
		return "Gem Cutter";
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

	public boolean addInventorySlotContents(int i, ItemStack itemstack) {
		if (items[i] != null) {

			if (items[i].isItemEqual(itemstack)) {
				items[i].stackSize += itemstack.stackSize;
			}
			if (items[i].stackSize > getInventoryStackLimit()) {
				items[i].stackSize = getInventoryStackLimit();
			}
		} else {
			setInventorySlotContents(i, itemstack);
		}
		return false;
	}

	/* NBT */
	@Override
	public void readFromNBT(NBTTagCompound tags) {
		super.readFromNBT(tags);
		readInventoryFromNBT(tags);
		cutting = tags.getBoolean("cutting");
		cut = tags.getInteger("cut");
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
		tags.setBoolean("cutting", cutting);
		tags.setInteger("cut", cut);
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

	public boolean refine() {
		if (getRecipeIndex() >= 0) {
			RecipeGemCutter recipe = RecipeRegistry.getGemCutterRecipes().get(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0))) {
				if (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(recipe.getOutput())) {
					decrStackSize(0, 1);
					cut++;
					storage.extractEnergy(250, false);
					if (cut == 1) {
						ItemStack out = recipe.getOutput().copy();
						out.stackSize = RecipeRegistry.getDropAmount(recipeIndex);
						addInventorySlotContents(1, out);
						cutting = false;
						cut = 0;
						setRecipeIndex(-1);
						PacketHandler.INSTANCE.sendToDimension(new MessageGemRefiner(xCoord, yCoord, zCoord, getRecipeIndex()), worldObj.provider.dimensionId);

					}
				}
				return true;
			}
		}
		cut = 0;
		setRecipeIndex(-1);
		cutting = false;
		return false;
	}

	public void setRefining(boolean infusing) {
		this.cutting = infusing;
		int number = -1;
		setRecipeIndex(number);
		if (getStackInSlot(0) != null)
			for (RecipeGemCutter recipe : RecipeRegistry.getGemCutterRecipes()) {
				number++;
				if (recipe.matchesExact(getStackInSlot(0))) {
					setRecipeIndex(number);
					break;
				}
			}
	}

	@Override
	public ForgeDirection[] getValidOutputs() {

		return new ForgeDirection[] { ForgeDirection.UNKNOWN };
	}

	@Override
	public ForgeDirection[] getValidInputs() {
		return ForgeDirection.VALID_DIRECTIONS;
	}
}
