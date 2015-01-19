package fluxedCrystals.tileEntity;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.RecipeGemCutter;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.network.MessageEnergyStorage;
import fluxedCrystals.network.MessageGemCutter;
import fluxedCrystals.network.PacketHandler;

/**
 * Created by Jared on 11/2/2014.
 */
public class TileEntityGemCutter extends TileEnergyBase implements IInventory, IManaReceiver {

	public ItemStack[] items;

	@Getter
	private boolean cutting = false;
	@Getter
	private int cut = 0;
	@Getter
	private int timePerCut = 0;
	@Getter
	int energy;

	@Getter
	@Setter
	private int recipeIndex;

	private int mana;
	private int MAX_MANA;
	private boolean RF = true;

	public TileEntityGemCutter() {
		super(10000);
		MAX_MANA = getMaxStorage();
		mana = 0;
		items = new ItemStack[6];

	}

	public void updateEntity() {

		if (energy != storage.getEnergyStored()) {
			PacketHandler.INSTANCE.sendToAll(new MessageEnergyStorage(this));
		}
		if (getStackInSlot(0) != null && !cutting) {
			PacketHandler.INSTANCE.sendToServer(new MessageGemCutter(xCoord, yCoord, zCoord));
		}
		if (worldObj != null) {
			if (worldObj.isRemote) {
				energy = storage.getEnergyStored();
			}
			if (storage.getEnergyStored() > 0) {
				if (!isUpgradeActive(new ItemStack(FCItems.upgradeMana)) && !isUpgradeActive(new ItemStack(FCItems.upgradeLP)) && !isUpgradeActive(new ItemStack(FCItems.upgradeEssentia))) {
					if (cutting && worldObj.getWorldTime() % getSpeed() == 0 && storage.getEnergyStored() >= getEffeciency() && getStackInSlot(1).stackSize < getStackInSlot(1).getMaxStackSize()) {
						refine();
						return;
					}
				} else {
					if (cutting && worldObj.getWorldTime() % getSpeed() == 0 && storage.getEnergyStored() >= getEffeciency()) {
						refine();
						return;
					}
				}
			}
			if (storage.getEnergyStored() <= 0) {

				if (getStackInSlot(5) != null) {
					if (getStackInSlot(1) != null && isUpgradeActive(new ItemStack(FCItems.upgradeLP))) {
						if (cutting && worldObj.getWorldTime() % getSpeed() == 0 && SoulNetworkHandler.canSyphonFromOnlyNetwork(getStackInSlot(5), getEffeciency() / 4) && getStackInSlot(1).stackSize < getStackInSlot(1).getMaxStackSize()) {
							refineLP();
							return;
						}
					} else {
						if (cutting && worldObj.getWorldTime() % getSpeed() == 0 && SoulNetworkHandler.canSyphonFromOnlyNetwork(getStackInSlot(5), getEffeciency() / 4) && isUpgradeActive(new ItemStack(FCItems.upgradeLP))) {
							refineLP();
							return;
						}
					}
				}
				if (getStackInSlot(1) != null && isUpgradeActive(new ItemStack(FCItems.upgradeMana))) {
					if (cutting && worldObj.getWorldTime() % getSpeed() == 0 && mana >= getEffeciency() && getStackInSlot(1).stackSize < getStackInSlot(1).getMaxStackSize()) {
						refineMana();
						return;
					}
				} else {
					if (cutting && isUpgradeActive(new ItemStack(FCItems.upgradeMana)) && worldObj.getWorldTime() % getSpeed() == 0 && mana >= getEffeciency()) {
						refineMana();
						return;
					}

				}
				if (FluxedCrystals.thaumcraftThere && isUpgradeActive(new ItemStack(FCItems.upgradeEssentia))) {
					if (AspectSourceHelper.findEssentia(this, Aspect.MECHANISM, ForgeDirection.UNKNOWN, 16)) {
						if (cutting && worldObj.getWorldTime() % getSpeed() == 0 && getStackInSlot(1) != null && getStackInSlot(1).stackSize < getStackInSlot(1).getMaxStackSize()) {
							if (refineEssentia()) {
								for (int i = 0; i < new Random().nextInt(16) + 1; i++)
									AspectSourceHelper.drainEssentia(this, Aspect.MECHANISM, ForgeDirection.UNKNOWN, 16);
							}
							return;
						} else {
							if (cutting && worldObj.getWorldTime() % getSpeed() == 0) {
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
		return (getUpgradeSlotOne() != null && getUpgradeSlotOne().isItemEqual(stack)) || (getUpgradeSlotTwo() != null && getUpgradeSlotTwo().isItemEqual(stack)) || (getUpgradeSlotThree() != null && getUpgradeSlotThree().isItemEqual(stack));
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
		mana = tags.getInteger("mana");
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
		tags.setInteger("mana", mana);
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
					if (cut == recipe.getInputamount()) {
						ItemStack out = recipe.getOutput().copy();
						out.stackSize = recipe.getOutputAmount();
						addInventorySlotContents(1, out);
						cutting = false;
						cut = 0;
						setRecipeIndex(-1);
						PacketHandler.INSTANCE.sendToDimension(new MessageGemCutter(xCoord, yCoord, zCoord, getRecipeIndex()), worldObj.provider.dimensionId);

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

	public boolean refineMana() {
		if (getRecipeIndex() >= 0) {
			RecipeGemCutter recipe = RecipeRegistry.getGemCutterRecipes().get(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0))) {
				if (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(recipe.getOutput())) {
					decrStackSize(0, 1);
					cut++;
					mana -= 250;
					if (cut == recipe.getInputamount()) {
						ItemStack out = recipe.getOutput().copy();
						addInventorySlotContents(1, out);
						out.stackSize = recipe.getOutputAmount();
						mana -= 500;
						cutting = false;
						cut = 0;
						setRecipeIndex(-1);
						PacketHandler.INSTANCE.sendToDimension(new MessageGemCutter(xCoord, yCoord, zCoord, getRecipeIndex()), worldObj.provider.dimensionId);

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

	public boolean refineLP() {
		if (getRecipeIndex() >= 0) {
			RecipeGemCutter recipe = RecipeRegistry.getGemCutterRecipes().get(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0))) {
				if (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(recipe.getOutput())) {
					decrStackSize(0, 1);
					cut++;
					SoulNetworkHandler.syphonFromNetwork(getStackInSlot(5), 250 / 4);
					if (cut == recipe.getInputamount()) {
						ItemStack out = recipe.getOutput().copy();
						out.stackSize = recipe.getOutputAmount();
						addInventorySlotContents(1, out);
						cutting = false;
						cut = 0;
						setRecipeIndex(-1);
						PacketHandler.INSTANCE.sendToDimension(new MessageGemCutter(xCoord, yCoord, zCoord, getRecipeIndex()), worldObj.provider.dimensionId);

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

	public boolean refineEssentia() {
		if (getRecipeIndex() >= 0) {
			RecipeGemCutter recipe = RecipeRegistry.getGemCutterRecipes().get(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0))) {
				if (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(recipe.getOutput())) {
					decrStackSize(0, 1);
					cut++;
					if (cut == recipe.getInputamount()) {
						ItemStack out = recipe.getOutput().copy();
						addInventorySlotContents(1, out);
						out.stackSize = recipe.getOutputAmount();
						cutting = false;
						cut = 0;
						setRecipeIndex(-1);
						PacketHandler.INSTANCE.sendToDimension(new MessageGemCutter(xCoord, yCoord, zCoord, getRecipeIndex()), worldObj.provider.dimensionId);

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

	public int getEnergyRemainingScaled(int amount) {
		System.out.println(storage.getEnergyStored());
		System.out.println(getMaxStorage());
		System.out.println(amount);
		return storage.getEnergyStored() * getMaxStorage() / amount;

	}

	public int getManaRemaningScaled(int amount) {
		System.out.println(getCurrentMana());
		System.out.println(MAX_MANA);
		System.out.println(amount);

		return getCurrentMana() * MAX_MANA / amount;
	}
}
