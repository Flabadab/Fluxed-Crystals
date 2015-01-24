package fluxedCrystals.tileEntity;

import tterrag.core.common.util.BlockCoord;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import fluxedCrystals.api.CrystalBase;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.blocks.FCBlocks;
import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.utils.Utils;

/**
 * Created by Jared on 11/2/2014.
 */
public class TileEntityPowerBlock extends TileEntity {

	private TileEntityManagerBlock manager;

	public TileEntityPowerBlock() {
	}

	public void setManager(TileEntityManagerBlock manager) {
		this.manager = manager;
	}

	public TileEntityManagerBlock getManager() {
		return manager;
	}

	public void invalidatePowerBlocks() {
		setManager(null);
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
}
