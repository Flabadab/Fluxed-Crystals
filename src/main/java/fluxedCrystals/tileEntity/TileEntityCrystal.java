package fluxedCrystals.tileEntity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.cbcore.LangUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tterrag.core.common.util.BlockCoord;
import fluxedCrystals.api.CrystalBase;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.compat.waila.IWailaInfo;
import fluxedCrystals.items.FCItems;

@Getter
@Setter
public class TileEntityCrystal extends TileEntity implements IWailaInfo {
	private int idx = 0;
	@Getter
	@Setter
	private int ticksgrown = 0;

	@Getter
	@Setter
	private boolean harvested = false;

	private TileEntityPowerBlock power;

	private ItemStack[] managerUpgrades = new ItemStack[3];
	private BlockCrystal crystal;

	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (power == null && getPowerTile(worldObj, new BlockCoord(this)) != null) {
				power = getPowerTile(worldObj, new BlockCoord(this));

			}
			if (crystal == null) {
				crystal = (BlockCrystal) worldObj.getBlock(xCoord, yCoord, zCoord);
			}
			if (power != null) {
				managerUpgrades = power.getManagerUpgrades();
				if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) < 7) {

					ticksgrown++;
					System.out.println(ticksgrown);
					if (power.getEnergyStored() > getUpgradeDrain(idx))
						if (ticksgrown >= RecipeRegistry.getGrowthTime(idx) / getSpeed()) {
							ticksgrown = 0;
							growPlant(worldObj, isUpgradeActive(new ItemStack(FCItems.upgradeNight)));
							power.storage.extractEnergy((getUpgradeDrain(idx)), false);
						}
				}
			}
			if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) >= 7) {
				if (power.getEnergyStored() >= 250 && isUpgradeActive(new ItemStack(FCItems.upgradeAutomation))) {
					crystal.dropCropDrops(worldObj, xCoord, yCoord, zCoord, 0, false);
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
					power.storage.extractEnergy(250, false);
				}
			}
		}
	}

	public boolean growPlant(World world, boolean night) {
		if (world != null)
			if (world.getBlock(xCoord, yCoord, zCoord) instanceof CrystalBase) {
				return ((CrystalBase) world.getBlock(xCoord, yCoord, zCoord)).growCrop(world, xCoord, yCoord, zCoord, world.rand, night);
			}
		return false;
	}

	@Override
	public boolean canUpdate() {
		return true;
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

	@Override
	public boolean shouldRefresh(Block oldBlock, Block newBlock, int oldMeta, int newMeta, World world, int x, int y, int z) {
		return oldBlock != newBlock;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}

	@Override
	public void getWailaInfo(List<String> tooltip, int x, int y, int z, World world) {
		float growthValue = (world.getBlockMetadata(x, y, z) / 7.0F) * 100.0F;
		if (growthValue < 100.0)
			tooltip.add(String.format("%s : %.0f %%", LangUtil.translateG("hud.msg.growth"), growthValue));
		else
			tooltip.add(String.format("%s : %s", LangUtil.translateG("hud.msg.growth"), LangUtil.translateG("hud.msg.mature")));

	}

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return new ItemStack(FCItems.shard, RecipeRegistry.getSeedReturn(idx), getIndex());
	}

	public TileEntityPowerBlock getPowerTile(World world, BlockCoord coord) {
		if (world.getTileEntity(coord.x, coord.y - 1, coord.z) != null && world.getTileEntity(coord.x, coord.y - 1, coord.z) instanceof TileEntityPowerBlock) {
			return (TileEntityPowerBlock) world.getTileEntity(coord.x, coord.y - 1, coord.z);
		}
		return null;
	}

	public boolean isUpgradeActive(ItemStack upgrade) {
		for (ItemStack upgrades : managerUpgrades) {
			if (upgrades != null)
				if (upgrades.isItemEqual(upgrade)) {
					return true;
				}
		}

		return false;
	}

	public int getSpeed() {
		int speed = 7;
		for (ItemStack item : managerUpgrades) {
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
		for (ItemStack item : managerUpgrades) {
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

	public int getUpgradeDrain(int idx) {
		int energy = RecipeRegistry.getPowerPerStage(idx);

		for (ItemStack item : managerUpgrades) {
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
