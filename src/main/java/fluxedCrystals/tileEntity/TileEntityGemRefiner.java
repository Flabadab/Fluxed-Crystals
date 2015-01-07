package fluxedCrystals.tileEntity;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.RecipeGemRefiner;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.network.MessageGemRefiner;
import fluxedCrystals.network.PacketHandler;
import ic2.api.energy.tile.IEnergySink;

import java.util.ArrayList;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectSourceHelper;
import vazkii.botania.api.mana.IManaReceiver;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;

/**
 * Created by Jared on 11/2/2014.
 */
public class TileEntityGemRefiner extends TileEnergyBase implements IInventory, IManaReceiver, IEnergySink {

	public ItemStack[] items;

	@Getter
	private boolean refining = false;
	@Getter
	private int refined = 0;

	@Getter
	@Setter
	private int recipeIndex;

	private int mana;
	private int MAX_MANA;
	private int MAX_ENERGY;
	private int current_Energy;
	private boolean addedToWorld = false;
	private boolean addedToEnergyNet = false;
	private boolean RF = true;

	public TileEntityGemRefiner() {
		super(10000);
		MAX_MANA = getMaxStorage();
		MAX_ENERGY = getMaxStorage() / 4;
		current_Energy = 0;
		mana = 0;
		items = new ItemStack[6];

	}

	public void updateEntity() {
		if (getStackInSlot(0) != null && !refining) {
			PacketHandler.INSTANCE.sendToServer(new MessageGemRefiner(xCoord, yCoord, zCoord));
		}
		if (worldObj != null) {
			if (storage.getEnergyStored() > 0) {
				if (getStackInSlot(1) != null) {
					if (refining && worldObj.getWorldTime() % getSpeed() == 0 && storage.getEnergyStored() >= getEffeciency() && getStackInSlot(1).stackSize < getStackInSlot(1).getMaxStackSize()) {
						refine();
						return;
					}
				} else {
					if (refining && worldObj.getWorldTime() % getSpeed() == 0 && storage.getEnergyStored() >= getEffeciency()) {
						refine();
						return;
					}
				}
			}
			if (storage.getEnergyStored() <= 0) {

				if (getStackInSlot(5) != null) {
					if (getStackInSlot(1) != null) {
						if (refining && worldObj.getWorldTime() % getSpeed() == 0 && SoulNetworkHandler.canSyphonFromOnlyNetwork(getStackInSlot(5), getEffeciency() / 4) && getStackInSlot(1).stackSize < getStackInSlot(1).getMaxStackSize()) {
							refineLP();
							return;
						}
					} else {
						if (refining && worldObj.getWorldTime() % getSpeed() == 0 && SoulNetworkHandler.canSyphonFromOnlyNetwork(getStackInSlot(5), getEffeciency() / 4)) {
							refineLP();
							return;
						}
					}
				}
				if (getStackInSlot(1) != null) {
					if (refining && worldObj.getWorldTime() % getSpeed() == 0 && mana >= getEffeciency() && getStackInSlot(1).stackSize < getStackInSlot(1).getMaxStackSize()) {
						refineMana();
						return;
					}
				} else {
					if (refining && worldObj.getWorldTime() % getSpeed() == 0 && mana >= getEffeciency()) {
						refineMana();
						return;
					}

				}
				if (refining && worldObj.getWorldTime() % getSpeed() == 0 && current_Energy >= getEffeciency() && getStackInSlot(1) != null && getStackInSlot(1).stackSize < getStackInSlot(1).getMaxStackSize()) {
					refineEU();
					return;
				} else {
					if (refining && worldObj.getWorldTime() % getSpeed() == 0 && current_Energy >= getEffeciency()) {
						refineEU();
						return;
					}

				}
				if (FluxedCrystals.thaumcraftThere) {
					if (AspectSourceHelper.findEssentia(this, Aspect.MECHANISM, ForgeDirection.UNKNOWN, 16)) {
						if (refining && worldObj.getWorldTime() % getSpeed() == 0 && getStackInSlot(1) != null && getStackInSlot(1).stackSize < getStackInSlot(1).getMaxStackSize()) {
							if (refineEssentia()) {
								for (int i = 0; i < new Random().nextInt(16) + 1; i++)
									AspectSourceHelper.drainEssentia(this, Aspect.MECHANISM, ForgeDirection.UNKNOWN, 16);
							}
							return;
						} else {
							if (refining && worldObj.getWorldTime() % getSpeed() == 0) {
								if (refineEssentia()) {
									for (int i = 0; i < new Random().nextInt(16) + 1; i++)
										AspectSourceHelper.drainEssentia(this, Aspect.MECHANISM, ForgeDirection.UNKNOWN, 16);
									return;
								}
							}
						}
					}
				}
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
		return "Gem Refiner";
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
		refining = tags.getBoolean("refining");
		refined = tags.getInteger("refined");
		setRecipeIndex(tags.getInteger("recipeIndex"));
		mana = tags.getInteger("mana");
		current_Energy = tags.getInteger("currentEnergy");
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
		tags.setBoolean("refining", refining);
		tags.setInteger("refined", refined);
		tags.setInteger("recipeIndex", getRecipeIndex());
		tags.setInteger("mana", mana);
		tags.setInteger("currentEnergy", current_Energy);
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
			RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipes().get(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0))) {
				if (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(recipe.getOutput())) {
					decrStackSize(0, 1);
					refined++;
					storage.extractEnergy(250, false);
					if (refined == RecipeRegistry.getRefinerAmount(recipeIndex)) {
						ItemStack out = recipe.getOutput().copy();
						out.stackSize = RecipeRegistry.getDropAmount(recipeIndex);
						addInventorySlotContents(1, out);
						refining = false;
						refined = 0;
						setRecipeIndex(-1);
						PacketHandler.INSTANCE.sendToDimension(new MessageGemRefiner(xCoord, yCoord, zCoord, getRecipeIndex()), worldObj.provider.dimensionId);

					}
				}
				return true;
			}
		}
		refined = 0;
		setRecipeIndex(-1);
		refining = false;
		return false;
	}

	public boolean refineMana() {
		if (getRecipeIndex() >= 0) {
			RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipes().get(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0))) {
				if (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(recipe.getOutput())) {
					decrStackSize(0, 1);
					refined++;
					mana -= 250;
					if (refined == RecipeRegistry.getRefinerAmount(recipeIndex)) {
						ItemStack out = recipe.getOutput().copy();
						out.stackSize = RecipeRegistry.getDropAmount(recipeIndex);
						addInventorySlotContents(1, out);
						refining = false;
						refined = 0;
						setRecipeIndex(-1);
						PacketHandler.INSTANCE.sendToDimension(new MessageGemRefiner(xCoord, yCoord, zCoord, getRecipeIndex()), worldObj.provider.dimensionId);

					}
				}
				return true;
			}
		}
		refined = 0;
		setRecipeIndex(-1);
		refining = false;
		return false;
	}

	public boolean refineLP() {
		if (getRecipeIndex() >= 0) {
			RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipes().get(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0))) {
				if (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(recipe.getOutput())) {
					decrStackSize(0, 1);
					refined++;
					SoulNetworkHandler.syphonFromNetwork(getStackInSlot(5), 250 / 4);
					if (refined == RecipeRegistry.getRefinerAmount(recipeIndex)) {
						ItemStack out = recipe.getOutput().copy();
						out.stackSize = RecipeRegistry.getDropAmount(recipeIndex);
						addInventorySlotContents(1, out);
						refining = false;
						refined = 0;
						setRecipeIndex(-1);
						PacketHandler.INSTANCE.sendToDimension(new MessageGemRefiner(xCoord, yCoord, zCoord, getRecipeIndex()), worldObj.provider.dimensionId);

					}
				}
				return true;
			}
		}
		refined = 0;
		setRecipeIndex(-1);
		refining = false;
		return false;
	}

	public boolean refineEU() {
		if (getRecipeIndex() >= 0) {
			RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipes().get(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0))) {
				if (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(recipe.getOutput())) {
					decrStackSize(0, 1);
					refined++;
					drainEnergy(250);
					if (refined == RecipeRegistry.getRefinerAmount(recipeIndex)) {
						ItemStack out = recipe.getOutput().copy();
						out.stackSize = RecipeRegistry.getDropAmount(recipeIndex);
						addInventorySlotContents(1, out);
						refining = false;
						refined = 0;
						setRecipeIndex(-1);
						PacketHandler.INSTANCE.sendToDimension(new MessageGemRefiner(xCoord, yCoord, zCoord, getRecipeIndex()), worldObj.provider.dimensionId);

					}
				}
				return true;
			}
		}
		refined = 0;
		setRecipeIndex(-1);
		refining = false;
		return false;
	}

	public boolean refineEssentia() {
		if (getRecipeIndex() >= 0) {
			RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipes().get(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0))) {
				if (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(recipe.getOutput())) {
					decrStackSize(0, 1);
					refined++;
					if (refined == RecipeRegistry.getRefinerAmount(recipeIndex)) {
						ItemStack out = recipe.getOutput().copy();
						out.stackSize = RecipeRegistry.getDropAmount(recipeIndex);
						addInventorySlotContents(1, out);
						refining = false;
						refined = 0;
						setRecipeIndex(-1);
						PacketHandler.INSTANCE.sendToDimension(new MessageGemRefiner(xCoord, yCoord, zCoord, getRecipeIndex()), worldObj.provider.dimensionId);

					}
				}
				return true;
			}
		}
		refined = 0;
		setRecipeIndex(-1);
		refining = false;
		return false;
	}

	public void setRefining(boolean infusing) {
		this.refining = infusing;
		int number = -1;
		setRecipeIndex(number);
		if (getStackInSlot(0) != null)
			for (RecipeGemRefiner recipe : RecipeRegistry.getGemRefinerRecipes()) {
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

	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
		return true;
	}

	@Override
	public double getDemandedEnergy() {
		return 32;
	}

	@Override
	public int getSinkTier() {
		return 1;
	}

	@Override
	public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
		double leftOver = amount;
		if (amount + current_Energy <= MAX_ENERGY) {
			current_Energy += amount;
			leftOver = 0;
		}
		if (amount + current_Energy > MAX_ENERGY) {
			current_Energy = MAX_ENERGY;
			leftOver = (current_Energy + amount) - MAX_ENERGY;
		}
		return leftOver;

	}

	public boolean drainEnergy(double amount) {
		return current_Energy - injectEnergy(null, -amount, 0) >= 0;
	}

	@Override
	public int getCurrentMana() {
		return mana;
	}

	@Override
	public boolean isFull() {
		return mana == MAX_MANA;
	}

	@Override
	public void recieveMana(int mana) {
		if (!isFull()) {
			this.mana += mana;
		}
		if (getCurrentMana() > MAX_MANA) {
			this.mana = MAX_MANA;
		}
	}

	@Override
	public boolean canRecieveManaFromBursts() {
		return true;
	}
}
