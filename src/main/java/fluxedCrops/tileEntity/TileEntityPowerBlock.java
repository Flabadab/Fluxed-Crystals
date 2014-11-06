package fluxedCrops.tileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import fluxedCrops.api.CropBase;

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

	public boolean growPlant() {
		if (worldObj.getBlock(xCoord, yCoord + 1, zCoord) instanceof CropBase) {
			return ((CropBase) worldObj.getBlock(xCoord, yCoord + 1, zCoord)).growCrop(worldObj, xCoord, yCoord + 1, zCoord, worldObj.rand);
		}
		return false;
	}

}
