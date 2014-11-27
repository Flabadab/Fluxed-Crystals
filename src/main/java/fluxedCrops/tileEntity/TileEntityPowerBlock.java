package fluxedCrops.tileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import fluxedCrops.api.CropBase;
import fluxedCrops.blocks.crops.BlockCrop;

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

	public void updateEntity() {

	}

	public boolean growPlant(World world, boolean night) {
		if (world != null)
			if (world.getBlock(xCoord, yCoord + 1, zCoord) instanceof CropBase) {
				return ((CropBase) world.getBlock(xCoord, yCoord + 1, zCoord)).growCrop(world, xCoord, yCoord + 1, zCoord, world.rand, night);
			}
		return false;
	}

	public BlockCrop getCrop(World world) {
		return world.getBlock(xCoord, yCoord + 1, zCoord) != null && world.getBlock(xCoord, yCoord + 1, zCoord) instanceof BlockCrop ? (BlockCrop) world.getBlock(xCoord, yCoord + 1, zCoord) : null;
	}

	public TileEntityCrop getCropTile(World world) {
		return world.getTileEntity(xCoord, yCoord + 1, zCoord) != null && world.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntityCrop ? (TileEntityCrop) world.getTileEntity(xCoord, yCoord + 1, zCoord) : null;
	}
}
