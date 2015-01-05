package fluxedCrystals.tileEntity;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.blocks.FCBlocks;
import fluxedCrystals.compat.waila.IWailaInfo;
import fluxedCrystals.items.FCItems;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import mcp.mobius.waila.Waila;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.AspectSourceHelper;
import tterrag.core.common.util.BlockCoord;
import vazkii.botania.api.mana.IManaReceiver;
import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;

/**
 * Created by Jared on 11/2/2014.
 */
public class TileEntityManagerBlock extends TileEnergyBase implements IInventory, IManager, IManaReceiver, IEnergySink {

	public ItemStack[] items;
	public int totalPowerBlocks;
	public boolean placedBlocks = false;
	public boolean placingPowerBlocks = false;
	private long lastTick;
	private boolean alertedPlayers = false;;

	private int size = 0;
	private int timer = 0;

	private int mana;
	private int MAX_MANA;
	private int MAX_ENERGY;
	private int current_Energy;
	private boolean addedToWorld = false;
	private boolean addedToEnergyNet = false;
	private boolean RF = true;
	@Getter
	private ArrayList<TileEntityPowerBlock> powerBlocks = new ArrayList<TileEntityPowerBlock>();
	@Getter
	private ArrayList<TileEntityPowerBlock> powerBlocksToAdd = new ArrayList<TileEntityPowerBlock>();

	public TileEntityManagerBlock() {
		super(100000);
		MAX_MANA = getMaxStorage();
		MAX_ENERGY = getMaxStorage() / 4;
		current_Energy = 0;
		mana = 0;
		items = new ItemStack[6];
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
		super.updateEntity();
		if (getEnergyStored() <= 0) {
			RF = false;
		}
		if (!worldObj.isRemote && !addedToWorld) {
			addedToWorld = true;
			addedToEnergyNet = true;
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
		}
		if (!(lastTick == worldObj.getTotalWorldTime())) {
			for (TileEntityPowerBlock power : powerBlocks) {
				power.convertblocks(getWorldObj(), this);

				if (power.getCropTile(worldObj) != null) {
					if (worldObj.getTotalWorldTime() % 20 == 0) {

						if (!RF) {
							if (getStackInSlot(5) != null) {

								if (getCurrentMana() > 0) {
									if (getCurrentMana() >= getUpgradeDrain(power.getCropTile(worldObj).getIndex()))
										if (worldObj.getTotalWorldTime() % (RecipeRegistry.getGrowthTime(power.getCropTile(worldObj).getIndex()) / getSpeed()) == 0)
											if (power.growPlant(worldObj, isUpgradeActive(new ItemStack(FCItems.upgradeNight)))) {
												mana -= -getUpgradeDrain(power.getCropTile(worldObj).getIndex());
											}
								}
								if (getStackInSlot(5).getItem() instanceof IBindable) {
									IBindable bindedItem = ((IBindable) getStackInSlot(5).getItem());
									if (worldObj.getTotalWorldTime() % (RecipeRegistry.getGrowthTime(power.getCropTile(worldObj).getIndex()) / getSpeed()) == 0)
										if (power.growPlant(worldObj, isUpgradeActive(new ItemStack(FCItems.upgradeNight)))) {
											SoulNetworkHandler.syphonFromNetwork(getStackInSlot(5), getUpgradeDrain(power.getCropTile(worldObj).getIndex()) / 4);
										}
								}

							}

						}

						if (isUpgradeActive(new ItemStack(FCItems.upgradeAutomation)))
							if (worldObj.getBlockMetadata(power.getCropTile(worldObj).xCoord, power.getCropTile(worldObj).yCoord, power.getCropTile(worldObj).zCoord) >= 7) {
								power.getCrop(worldObj).dropCropDrops(worldObj, power.getCropTile(worldObj).xCoord, power.getCropTile(worldObj).yCoord, power.getCropTile(worldObj).zCoord, 0);
								worldObj.setBlockMetadataWithNotify(power.getCropTile(worldObj).xCoord, power.getCropTile(worldObj).yCoord, power.getCropTile(worldObj).zCoord, 0, 3);
								this.storage.extractEnergy(100, false);
							}
					}
					if (!RF) {
						if (worldObj.getTotalWorldTime() % (RecipeRegistry.getGrowthTime(power.getCropTile(worldObj).getIndex()) / getSpeed()) == 0)
							if (drainEnergy(getUpgradeDrain(power.getCropTile(worldObj).getIndex() / 4)))
								power.growPlant(worldObj, isUpgradeActive(new ItemStack(FCItems.upgradeNight)));
					}

					if (RecipeRegistry.getAspectNeeded(power.getCropTile(worldObj).getIndex()) != null) {

						if (AspectSourceHelper.findEssentia(power.getCropTile(worldObj), RecipeRegistry.getAspectNeeded(power.getCropTile(worldObj).getIndex()), ForgeDirection.UNKNOWN, 15))
							if (worldObj.getTotalWorldTime() % (RecipeRegistry.getGrowthTime(power.getCropTile(worldObj).getIndex()) / getSpeed()) == 0)
								if (power.growPlant(worldObj, isUpgradeActive(new ItemStack(FCItems.upgradeNight)))) {
									for (int i = 0; i < RecipeRegistry.getAspectNeededAmount(power.getCropTile(worldObj).getIndex()); i++)
										AspectSourceHelper.drainEssentia(power.getCropTile(worldObj), RecipeRegistry.getAspectNeeded(power.getCropTile(worldObj).getIndex()), ForgeDirection.UNKNOWN, 15);
								}
					}
					if (RecipeRegistry.getAspectNeeded(power.getCropTile(worldObj).getIndex()) == null)
						if (this.storage.getEnergyStored() > getUpgradeDrain(power.getCropTile(worldObj).getIndex()))
							if (worldObj.getTotalWorldTime() % (RecipeRegistry.getGrowthTime(power.getCropTile(worldObj).getIndex()) / getSpeed()) == 0)
								if (power.growPlant(worldObj, isUpgradeActive(new ItemStack(FCItems.upgradeNight)))) {
									this.storage.extractEnergy(getUpgradeDrain(power.getCropTile(worldObj).getIndex()), false);
								}
				}
			}
			if (!powerBlocksToAdd.isEmpty()) {
				for (TileEntityPowerBlock power : powerBlocksToAdd) {
					powerBlocks.add(power);
				}
				powerBlocksToAdd.clear();
			}

			if (worldObj.getBlock(xCoord + 1, yCoord, zCoord) == Blocks.dirt) {
				if (drainPower(250)) {
					worldObj.setBlock(xCoord + 1, yCoord, zCoord, FCBlocks.powerBlock);
					storage.extractEnergy(250, false);
					((TileEntityPowerBlock) worldObj.getTileEntity(xCoord + 1, yCoord, zCoord)).setManager(this);
					powerBlocks.add((TileEntityPowerBlock) worldObj.getTileEntity(xCoord + 1, yCoord, zCoord));
				}
			}
			if (worldObj.getBlock(xCoord - 1, yCoord, zCoord) == Blocks.dirt) {
				if (drainPower(250)) {
					worldObj.setBlock(xCoord - 1, yCoord, zCoord, FCBlocks.powerBlock);
					storage.extractEnergy(250, false);
					((TileEntityPowerBlock) worldObj.getTileEntity(xCoord - 1, yCoord, zCoord)).setManager(this);
					powerBlocks.add((TileEntityPowerBlock) worldObj.getTileEntity(xCoord - 1, yCoord, zCoord));
				}
			}
			if (worldObj.getBlock(xCoord, yCoord, zCoord + 1) == Blocks.dirt) {
				if (drainPower(250)) {
					worldObj.setBlock(xCoord, yCoord, zCoord + 1, FCBlocks.powerBlock);
					storage.extractEnergy(250, false);
					((TileEntityPowerBlock) worldObj.getTileEntity(xCoord, yCoord, zCoord + 1)).setManager(this);
					powerBlocks.add((TileEntityPowerBlock) worldObj.getTileEntity(xCoord, yCoord, zCoord + 1));
				}
			}
			if (worldObj.getBlock(xCoord, yCoord, zCoord - 1) == Blocks.dirt) {
				if (drainPower(250)) {
					worldObj.setBlock(xCoord, yCoord, zCoord - 1, FCBlocks.powerBlock);
					storage.extractEnergy(250, false);
					((TileEntityPowerBlock) worldObj.getTileEntity(xCoord, yCoord, zCoord - 1)).setManager(this);
					powerBlocks.add((TileEntityPowerBlock) worldObj.getTileEntity(xCoord, yCoord, zCoord - 1));
				}
			}
		}
		lastTick = worldObj.getTotalWorldTime();
	}

	public void placePowerBlocks(int size) {
		for (int x = (size - (size * 2)); x <= size; x++) {
			for (int z = (size - (size * 2)); z <= size; z++) {
				if (worldObj.getBlock(xCoord + x, yCoord, zCoord + z) == FCBlocks.managerBlock) {
					// NO-OP
				} else {
					if (worldObj.getBlock(xCoord + x, yCoord, zCoord + z).isReplaceable(worldObj, xCoord + x, yCoord, zCoord + z)) {

						int items = 0;

						if (getStackInSlot(0) != null) {
							items += getStackInSlot(0).stackSize;
						}
						if (getStackInSlot(1) != null) {
							items += getStackInSlot(1).stackSize;
						}
						if (getStackInSlot(0) != null && items >= 0) {
							decrStackSize(0, 1);
							worldObj.setBlock(xCoord + x, yCoord, zCoord + z, FCBlocks.powerBlock);
							((TileEntityPowerBlock) worldObj.getTileEntity(xCoord + x, yCoord, zCoord + z)).setManager(this);
							powerBlocks.add((TileEntityPowerBlock) worldObj.getTileEntity(xCoord + x, yCoord, zCoord + z));
							placedBlocks = true;

						} else if (getStackInSlot(1) != null && items >= 0) {
							decrStackSize(1, 1);
							worldObj.setBlock(xCoord + x, yCoord, zCoord + z, FCBlocks.powerBlock);
							((TileEntityPowerBlock) worldObj.getTileEntity(xCoord + x, yCoord, zCoord + z)).setManager(this);
							powerBlocks.add((TileEntityPowerBlock) worldObj.getTileEntity(xCoord + x, yCoord, zCoord + z));
							placedBlocks = true;
						}

					}
				}
			}
		}

	}

	public void placePowerBlocks() {
		placePowerBlocks(size);

	}

	public boolean canPlacePowerBlocks(int size) {
		int items = 0;
		if (getStackInSlot(0) != null) {
			items += getStackInSlot(0).stackSize;
		}
		if (getStackInSlot(1) != null) {
			items += getStackInSlot(1).stackSize;
		}
		if (size * size > items) {
			return false;
		}
		for (int x = (size - (size * 2)); x <= size; x++) {
			for (int z = (size - (size * 2)); z <= size; z++) {

				if (worldObj.getBlock(xCoord + x, yCoord, zCoord + z) == FCBlocks.managerBlock) {
					// NO-OP
				} else {
					if (!worldObj.getBlock(xCoord + x, yCoord, zCoord + z).isReplaceable(worldObj, xCoord + x, yCoord, zCoord + z)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean canPlacePowerBlocks() {
		return canPlacePowerBlocks(size);

	}

	public void invalidatePowerBlocks() {
		for (TileEntityPowerBlock power : powerBlocks) {
			power.setManager(null);
		}
	}

	public int getTotalPowerBlocks() {
		return totalPowerBlocks;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String getInventoryName() {
		return "Manager";
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
		if (stack == new ItemStack(FCBlocks.powerBlock, 0, OreDictionary.WILDCARD_VALUE)) {
			return true;
		}
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
		readPowerBlocksFromNBT(tags);
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

	public void readPowerBlocksFromNBT(NBTTagCompound tags) {
		NBTTagList nbttaglist = tags.getTagList("powerBlocks", Constants.NBT.TAG_COMPOUND);
		for (int iter = 0; iter < nbttaglist.tagCount(); iter++) {
			TileEntityPowerBlock tile = new TileEntityPowerBlock();
			tile.readFromNBT(nbttaglist.getCompoundTagAt(iter));
			powerBlocks.add(tile);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tags) {
		super.writeToNBT(tags);
		writeInventoryToNBT(tags);
		WritePowerBlocksToNBT(tags);
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

	public void WritePowerBlocksToNBT(NBTTagCompound tags) {
		NBTTagList nbtlist = new NBTTagList();

		for (TileEntityPowerBlock power : powerBlocks) {
			NBTTagCompound tagList = new NBTTagCompound();
			power.writeToNBT(tagList);
			nbtlist.appendTag(tagList);
		}

		tags.setTag("powerBlocks", nbtlist);
	}

	public ArrayList<ItemStack> getUpgrades() {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(getUpgradeOne());
		list.add(getUpgradeTwo());
		list.add(getUpgradeThree());
		return list;
	}

	public ItemStack getUpgradeOne() {
		return getStackInSlot(2);
	}

	public ItemStack getUpgradeTwo() {
		return getStackInSlot(3);
	}

	public ItemStack getUpgradeThree() {
		return getStackInSlot(4);
	}

	public int getSpeed() {
		int speed = 7;
		for (ItemStack item : getUpgrades()) {
			if (item != null) {
				if (item.isItemEqual(new ItemStack(FCItems.upgradeSpeed))) {
					speed += 2;
				}
			}
		}
		return speed;
	}

	public int getEffeciency() {
		int eff = 0;
		for (ItemStack item : getUpgrades()) {
			if (item != null) {
				if (item.isItemEqual(new ItemStack(FCItems.upgradeEffeciency))) {
					eff += 15;
				}
			}
		}
		if (eff == 0) {
			eff = 1;
		}
		return eff;
	}

	public boolean isUpgradeActive(ItemStack stack) {
		return (getUpgradeOne() != null && getUpgradeOne().isItemEqual(stack)) || (getUpgradeTwo() != null && getUpgradeTwo().isItemEqual(stack)) || (getUpgradeThree() != null && getUpgradeThree().isItemEqual(stack));
	}

	public int getUpgradeDrain(int idx) {
		int energy = RecipeRegistry.getPowerPerStage(idx);

		for (ItemStack item : getUpgrades()) {
			if (item != null) {
				if (item.isItemEqual(new ItemStack(FCItems.upgradeNight))) {
					energy += energy / 15;
				}
				if (item.isItemEqual(new ItemStack(FCItems.upgradeSpeed))) {
					energy += energy / 12;
				}
				if (item.isItemEqual(new ItemStack(FCItems.upgradeAutomation))) {
					energy += energy / 8;
				}
			}
		}

		if (isUpgradeActive(new ItemStack(FCItems.upgradeEffeciency))) {
			energy /= getEffeciency();
		}

		return energy;
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

	public int getTileIndex(TileEntityPowerBlock tile) {
		return tile.getCropTile(getWorldObj()).getIndex();
	}

	@Override
	public BlockCoord getCordinates() {
		return new BlockCoord(this);
	}

	@Override
	public void validate() {
		super.validate();
		if (!addedToEnergyNet) {
			addedToWorld = false;
		}
	}

	@Override
	public void invalidate() {
		if (addedToEnergyNet) {
			if (!worldObj.isRemote) {
				MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			}
			addedToEnergyNet = false;
		}
		super.invalidate();
	}

	public boolean drainPower(int amount) {
		if (storage.getEnergyStored() >= amount) {
			storage.extractEnergy(amount, false);
			return true;
		}
		if (getCurrentMana() >= amount) {
			recieveMana(-amount);
			return true;
		}
		if (current_Energy >= amount / 4) {
			drainEnergy(amount / 4);
			return true;
		}
		if (getStackInSlot(5) != null) {
			if (getStackInSlot(5).getItem() instanceof IBindable) {
				SoulNetworkHandler.syphonFromNetwork(getStackInSlot(5), amount / 4);
				return true;
			}
		}
		return false;
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

}
