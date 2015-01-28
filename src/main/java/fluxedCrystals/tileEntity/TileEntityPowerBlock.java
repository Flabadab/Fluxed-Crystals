package fluxedCrystals.tileEntity;

import java.util.EnumSet;

import tterrag.core.common.util.BlockCoord;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import fluxedCrystals.api.CrystalBase;
import fluxedCrystals.blocks.crystal.BlockCrystal;

/**
 * Created by Jared on 11/2/2014.
 */
@Getter
@Setter
public class TileEntityPowerBlock extends TileEnergyBase {

	private TileEntityManagerBlock manager;
	public ItemStack[] managerUpgrades = new ItemStack[3];

	public int managerX = 0;
	public int managerY = 0;
	public int managerZ = 0;

	public TileEntityPowerBlock() {
		super(10000);

	}

	public boolean canUpdate() {
		return false;
	}


	public void setManager(TileEntityManagerBlock manager) {
		this.manager = manager;
	}

	public TileEntityManagerBlock getManager() {
		return manager;
	}


	public boolean growPlant(World world, boolean night) {
		if (world != null)
			if (world.getBlock(xCoord, yCoord + 1, zCoord) instanceof CrystalBase) {
				TileEntityCrystal crystal = (TileEntityCrystal) world.getTileEntity(xCoord, yCoord + 1, zCoord);
				return ((CrystalBase) world.getBlock(xCoord, yCoord + 1, zCoord)).growCrop(world, xCoord, yCoord + 1, zCoord, world.rand, night);
			}
		return false;
	}

	public BlockCrystal getCrop(World world) {
		return world.getBlock(xCoord, yCoord + 1, zCoord) != null && world.getBlock(xCoord, yCoord + 1, zCoord) instanceof BlockCrystal ? (BlockCrystal) world.getBlock(xCoord, yCoord + 1, zCoord) : null;
	}

	public TileEntityCrystal getCropTile(World world) {
		return world.getTileEntity(xCoord, yCoord + 1, zCoord) != null && world.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntityCrystal ? (TileEntityCrystal) world.getTileEntity(xCoord, yCoord + 1, zCoord) : null;
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

	}

	@Override
	public EnumSet<ForgeDirection> getValidOutputs() {
		return EnumSet.noneOf(ForgeDirection.class);
	}

	@Override
	public EnumSet<ForgeDirection> getValidInputs() {
		EnumSet<ForgeDirection> set = EnumSet.allOf(ForgeDirection.class);
		set.remove(ForgeDirection.UP);
		return set;
	}

}
