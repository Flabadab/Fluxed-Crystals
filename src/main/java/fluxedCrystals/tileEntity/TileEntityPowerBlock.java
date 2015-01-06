package fluxedCrystals.tileEntity;

import tterrag.core.common.util.BlockCoord;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
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

	public void convertblocks(World world, TileEntityManagerBlock manager) {
		if (world.getBlock(xCoord + 1, yCoord, zCoord) != null)
			if (world.getBlock(xCoord + 1, yCoord, zCoord) == Blocks.dirt) {
				world.setBlock(xCoord + 1, yCoord, zCoord, FCBlocks.powerBlock);
				((TileEntityPowerBlock) world.getTileEntity(xCoord + 1, yCoord, zCoord)).setManager(getManager());
				manager.getPowerBlocksToAdd().add((TileEntityPowerBlock) world.getTileEntity(xCoord + 1, yCoord, zCoord));
			}
		if (world.getBlock(xCoord - 1, yCoord, zCoord) != null)
			if (world.getBlock(xCoord - 1, yCoord, zCoord) == Blocks.dirt) {
				world.setBlock(xCoord - 1, yCoord, zCoord, FCBlocks.powerBlock);
				((TileEntityPowerBlock) world.getTileEntity(xCoord - 1, yCoord, zCoord)).setManager(getManager());
				manager.getPowerBlocksToAdd().add((TileEntityPowerBlock) world.getTileEntity(xCoord - 1, yCoord, zCoord));
			}
		if (world.getBlock(xCoord, yCoord, zCoord + 1) != null)
			if (world.getBlock(xCoord, yCoord, zCoord + 1) == Blocks.dirt) {
				world.setBlock(xCoord, yCoord, zCoord + 1, FCBlocks.powerBlock);
				((TileEntityPowerBlock) world.getTileEntity(xCoord, yCoord, zCoord + 1)).setManager(getManager());
				manager.getPowerBlocksToAdd().add((TileEntityPowerBlock) world.getTileEntity(xCoord, yCoord, zCoord + 1));
			}
		if (world.getBlock(xCoord, yCoord, zCoord - 1) != null)
			if (world.getBlock(xCoord, yCoord, zCoord - 1) == Blocks.dirt) {
				world.setBlock(xCoord, yCoord, zCoord - 1, FCBlocks.powerBlock);
				((TileEntityPowerBlock) world.getTileEntity(xCoord, yCoord, zCoord - 1)).setManager(getManager());
				manager.getPowerBlocksToAdd().add((TileEntityPowerBlock) world.getTileEntity(xCoord, yCoord, zCoord - 1));
			}
	}

	public void updateEntity() {
	}

	public boolean growPlant(World world, boolean night, int dimension) {
		if (world != null)
			if (world.getBlock(xCoord, yCoord + 1, zCoord) instanceof CrystalBase) {
				TileEntityCrystal crystal = (TileEntityCrystal) world.getTileEntity(xCoord, yCoord + 1, zCoord);
				if (RecipeRegistry.getDimensionWhitelist(crystal.getIndex()) == world.provider.dimensionId)
					return ((CrystalBase) world.getBlock(xCoord, yCoord + 1, zCoord)).growCrop(world, xCoord, yCoord + 1, zCoord, world.rand, night);
			}

		return false;
	}

	public BlockCrystal getCrop(World world) {
		return world.getBlock(xCoord, yCoord + 1, zCoord) != null && world.getBlock(xCoord, yCoord + 1, zCoord) instanceof BlockCrystal ? (BlockCrystal) world.getBlock(xCoord, yCoord + 1, zCoord) : null;
	}

	public TileEntityCrystal getCropTile(World world) {
		if (world != null)
			return world.getTileEntity(xCoord, yCoord + 1, zCoord) != null && world.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntityCrystal ? (TileEntityCrystal) world.getTileEntity(xCoord, yCoord + 1, zCoord) : null;
		return null;
	}
}
