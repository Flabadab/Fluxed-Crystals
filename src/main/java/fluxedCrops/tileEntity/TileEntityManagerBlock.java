package fluxedCrops.tileEntity;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.blocks.FCBlocks;
import fluxedCrops.items.FCItems;
import fluxedCrops.network.MessageEnergyUpdate;
import fluxedCrops.network.PacketHandler;
import fluxedCrops.utils.Utils;

/**
 * Created by Jared on 11/2/2014.
 */
public class TileEntityManagerBlock extends TileEnergyBase implements IInventory {
	public ItemStack[] items;
	public int totalPowerBlocks;

	public boolean placedBlocks = false;

	public boolean placingPowerBlocks = false;

	private int size = 0;
	private int timer = 0;

	private ArrayList<TileEntityPowerBlock> powerBlocks = new ArrayList<TileEntityPowerBlock>();

	public TileEntityManagerBlock() {
		super(100000);
		items = new ItemStack[5];
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
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		for (TileEntityPowerBlock power : powerBlocks) {
			if (power.getCropTile(worldObj) != null) {
				if (worldObj.getWorldTime() % 20 == 0) {
					if (isUpgradeActive(new ItemStack(FCItems.upgradeAutomation)))
						if (worldObj.getBlockMetadata(power.getCropTile(worldObj).xCoord, power.getCropTile(worldObj).yCoord, power.getCropTile(worldObj).zCoord) >= 7) {
							power.getCrop(worldObj).dropCropDrops(worldObj, power.getCropTile(worldObj).xCoord, power.getCropTile(worldObj).yCoord, power.getCropTile(worldObj).zCoord);
							worldObj.setBlockMetadataWithNotify(power.getCropTile(worldObj).xCoord, power.getCropTile(worldObj).yCoord, power.getCropTile(worldObj).zCoord, 0, 3);
							this.storage.extractEnergy(100, false);
						}
				}
				if (this.storage.getEnergyStored() > getUpgradeDrain(power.getCropTile(worldObj).getIndex()))
					if (worldObj.getWorldTime() % (RecipeRegistry.getGrowthTime(power.getCropTile(worldObj).getIndex()) / getSpeed()) == 0)
						if (power.growPlant(worldObj, isUpgradeActive(new ItemStack(FCItems.upgradeNight)))) {
							this.storage.extractEnergy(getUpgradeDrain(power.getCropTile(worldObj).getIndex()), false);
						}
			}
		}
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
							if (!worldObj.isRemote)
								PacketHandler.INSTANCE.sendToServer(new MessageEnergyUpdate(xCoord, yCoord, zCoord, storage.getEnergyStored() - 250));

						} else if (getStackInSlot(1) != null && items >= 0) {
							decrStackSize(1, 1);
							worldObj.setBlock(xCoord + x, yCoord, zCoord + z, FCBlocks.powerBlock);
							((TileEntityPowerBlock) worldObj.getTileEntity(xCoord + x, yCoord, zCoord + z)).setManager(this);
							powerBlocks.add((TileEntityPowerBlock) worldObj.getTileEntity(xCoord + x, yCoord, zCoord + z));
							placedBlocks = true;
							if (!worldObj.isRemote)
								PacketHandler.INSTANCE.sendToServer(new MessageEnergyUpdate(xCoord, yCoord, zCoord, storage.getEnergyStored() - 250));
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

}
