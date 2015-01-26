package fluxedCrystals.tileEntity;

import java.util.ArrayList;
import java.util.EnumSet;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import tterrag.core.common.util.BlockCoord;
import vazkii.botania.api.mana.IManaReceiver;
import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.blocks.FCBlocks;
import fluxedCrystals.items.FCItems;

/**
 * Created by Jared on 11/2/2014.
 */
public class TileEntityManagerBlock extends TileEnergyBase implements IInventory, IManager, IManaReceiver {

	public ItemStack[] items;
	public int totalPowerBlocks;
	public boolean placedBlocks = false;
	private long lastTick;

	private int size = 1;
	private int timer = 0;

	private int mana;
	private int MAX_MANA;
	private boolean RF = true;
	private int energy = 0;

	@Getter
	private ArrayList<TileEntityPowerBlock> powerBlocks = new ArrayList<TileEntityPowerBlock>();
	@Getter
	private ArrayList<TileEntityPowerBlock> powerBlocksToRemove = new ArrayList<TileEntityPowerBlock>();

	@Setter
	private boolean converting;

	public TileEntityManagerBlock() {
		super(100000);
		MAX_MANA = getMaxStorage();
		mana = 0;
		items = new ItemStack[6];
	}

	@Override
	public EnumSet<ForgeDirection> getValidOutputs() {
		return EnumSet.noneOf(ForgeDirection.class);
	}

	@Override
	public EnumSet<ForgeDirection> getValidInputs() {
		return EnumSet.allOf(ForgeDirection.class);
	}

	public void updateEntity() {
		super.updateEntity();
		if (getStackInSlot(0) != null) {
			if (size != 2 && getStackInSlot(0).isItemEqual(new ItemStack(FCItems.upgradeRangeBasic))) {
				size = 2;
			}
			if (size != 3 && getStackInSlot(0).isItemEqual(new ItemStack(FCItems.upgradeRangeGreater))) {
				size = 3;
			}
			if (size != 4 && getStackInSlot(0).isItemEqual(new ItemStack(FCItems.upgradeRangeAdvanced))) {
				size = 4;
			}
		}
		if (getStackInSlot(0) == null && size >= 1) {
			powerBlocks.clear();
			size = 1;
		}
		if (canPlacePowerBlocks(size)) {
			placePowerBlocks(size);
		}
		if (worldObj.getTotalWorldTime() % 20 == 0) {
			for (TileEntityPowerBlock power : powerBlocks) {
				if (power.getManager() == null) {
					power.setManager(this);
				}
				if (power.getWorldObj() == null) {
					powerBlocksToRemove.add(power);
				}
				power.setManagerUpgrades(new ItemStack[] { getUpgradeOne(), getUpgradeTwo(), getUpgradeThree() });
				if (power.getEnergyStored() < power.getMaxStorage() && storage.getEnergyStored() >= 500) {
					power.storage.receiveEnergy(500, false);
					storage.extractEnergy(500, false);
				}

			}
			for (TileEntityPowerBlock power : powerBlocksToRemove) {
				powerBlocks.remove(power);
			}
		}
		// if (!powerBlocks.isEmpty()) {
		// for (int j = 0; j < powerBlocks.size(); j++) {
		//
		// TileEntityPowerBlock power =
		// powerBlocks.get(worldObj.rand.nextInt(powerBlocks.size()));
		// if (power != null)
		// if (power.getCropTile(worldObj) != null) {
		// if (isUpgradeActive(new ItemStack(FCItems.upgradeMana))) {
		// if (getCurrentMana() > 0) {
		// if (getCurrentMana() >=
		// getUpgradeDrain(power.getCropTile(worldObj).getIndex()))
		// if (worldObj.getTotalWorldTime() %
		// (RecipeRegistry.getGrowthTime(power.getCropTile(worldObj).getIndex())
		// / getSpeed()) == 0)
		// if (power.growPlant(worldObj, isUpgradeActive(new
		// ItemStack(FCItems.upgradeNight)))) {
		// mana -= -getUpgradeDrain(power.getCropTile(worldObj).getIndex());
		// }
		// }
		// }
		// if (isUpgradeActive(new ItemStack(FCItems.upgradeLP))) {
		// if (getStackInSlot(4) != null) {
		// if (getStackInSlot(4).getItem() instanceof IBindable) {
		// IBindable bindedItem = ((IBindable) getStackInSlot(4).getItem());
		// if (worldObj.getTotalWorldTime() %
		// (RecipeRegistry.getGrowthTime(power.getCropTile(worldObj).getIndex())
		// / getSpeed()) == 0)
		// if (power.growPlant(worldObj, isUpgradeActive(new
		// ItemStack(FCItems.upgradeNight)))) {
		// SoulNetworkHandler.syphonFromNetwork(getStackInSlot(4),
		// getUpgradeDrain(power.getCropTile(worldObj).getIndex()) / 4);
		// }
		// }
		// }
		// }
		// if (isUpgradeActive(new ItemStack(FCItems.upgradeAutomation)))
		// if (worldObj.getBlockMetadata(power.getCropTile(worldObj).xCoord,
		// power.getCropTile(worldObj).yCoord,
		// power.getCropTile(worldObj).zCoord) >= 7) {
		// power.getCrop(worldObj).dropCropDrops(worldObj,
		// power.getCropTile(worldObj).xCoord,
		// power.getCropTile(worldObj).yCoord,
		// power.getCropTile(worldObj).zCoord, 0, false);
		// worldObj.setBlockMetadataWithNotify(power.getCropTile(worldObj).xCoord,
		// power.getCropTile(worldObj).yCoord,
		// power.getCropTile(worldObj).zCoord, 0, 3);
		// this.storage.extractEnergy(100, false);
		// }
		// if (FluxedCrystals.thaumcraftThere && isUpgradeActive(new
		// ItemStack(FCItems.upgradeEssentia))) {
		// if (ConfigProps.aspect != null) {
		// if (AspectSourceHelper.findEssentia(power.getCropTile(worldObj),
		// ConfigProps.aspect, ForgeDirection.UNKNOWN, ConfigProps.aspectRange))
		// if (worldObj.getTotalWorldTime() %
		// (RecipeRegistry.getGrowthTime(power.getCropTile(worldObj).getIndex())
		// / getSpeed()) == 0)
		// if (power.growPlant(worldObj, isUpgradeActive(new
		// ItemStack(FCItems.upgradeNight)))) {
		// for (int i = 0; i <
		// RecipeRegistry.getAspectNeededAmount(power.getCropTile(worldObj).getIndex());
		// i++)
		// AspectSourceHelper.drainEssentia(power.getCropTile(worldObj),
		// ConfigProps.aspect, ForgeDirection.UNKNOWN, ConfigProps.aspectRange);
		// }
		// }
		// }
		// if (!isUpgradeActive(new ItemStack(FCItems.upgradeEssentia)) &&
		// !isUpgradeActive(new ItemStack(FCItems.upgradeMana)) &&
		// !isUpgradeActive(new ItemStack(FCItems.upgradeLP)))
		// if (this.storage.getEnergyStored() >
		// getUpgradeDrain(power.getCropTile(worldObj).getIndex()))
		// if (worldObj.getWorldInfo().getWorldTime() %
		// (RecipeRegistry.getGrowthTime(power.getCropTile(worldObj).getIndex())
		// / getSpeed()) == 0)
		// if (power.growPlant(worldObj, isUpgradeActive(new
		// ItemStack(FCItems.upgradeNight)))) {
		// this.storage.extractEnergy(getUpgradeDrain(power.getCropTile(worldObj).getIndex()),
		// false);
		// }
		// }
		// }
		// }

	}

	public void placePowerBlocks(int size) {
		for (int x = (size - (size * 2)); x <= size; x++) {
			for (int z = (size - (size * 2)); z <= size; z++) {
				if (worldObj.getBlock(xCoord + x, yCoord, zCoord + z) == FCBlocks.managerBlock) {
					// NO-OP
				} else if (storage.getEnergyStored() > 250) {
					if (worldObj.getBlock(xCoord + x, yCoord, zCoord + z) == Blocks.dirt || worldObj.getBlock(xCoord + x, yCoord, zCoord + z) == Blocks.grass || worldObj.getBlock(xCoord + x, yCoord, zCoord + z) == FCBlocks.powerBlock) {
						worldObj.setBlock(xCoord + x, yCoord, zCoord + z, FCBlocks.powerBlock);
						((TileEntityPowerBlock) worldObj.getTileEntity(xCoord + x, yCoord, zCoord + z)).setManager(this);
						powerBlocks.add((TileEntityPowerBlock) worldObj.getTileEntity(xCoord + x, yCoord, zCoord + z));
						placedBlocks = true;
						if (!(worldObj.getBlock(xCoord + x, yCoord, zCoord + z) == FCBlocks.powerBlock))
							storage.extractEnergy(250, false);
					}
				}
			}
		}

	}

	public void placePowerBlocks() {
		placePowerBlocks(size);

	}

	public boolean canPlacePowerBlocks(int size) {
		ArrayList<Boolean> returnBool = new ArrayList<Boolean>();
		for (int x = (size - (size * 2)); x <= size; x++) {
			for (int z = (size - (size * 2)); z <= size; z++) {
				if (worldObj.getBlock(xCoord + x, yCoord, zCoord + z) == FCBlocks.managerBlock) {
				}

				if (worldObj.getBlock(xCoord + x, yCoord, zCoord + z) == Blocks.dirt || worldObj.getBlock(xCoord + x, yCoord, zCoord + z) == Blocks.grass || worldObj.getBlock(xCoord + x, yCoord, zCoord + z) == FCBlocks.powerBlock) {
					returnBool.add(true);
				}
			}
		}
		if (returnBool.contains(true)) {
			return true;
		}
		return false;
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
		return true;
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
		return getStackInSlot(1);
	}

	public ItemStack getUpgradeTwo() {
		return getStackInSlot(2);
	}

	public ItemStack getUpgradeThree() {
		return getStackInSlot(3);
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

	public boolean drainPower(int amount) {
		if (storage.getEnergyStored() >= amount) {
			storage.extractEnergy(amount, false);
			return true;
		}
		if (getCurrentMana() >= amount) {
			recieveMana(-amount);
			return true;
		}
		if (getStackInSlot(4) != null) {
			if (getStackInSlot(4).getItem() instanceof IBindable) {
				SoulNetworkHandler.syphonFromNetwork(getStackInSlot(4), amount / 4);
				return true;
			}
		}
		return false;
	}

}
