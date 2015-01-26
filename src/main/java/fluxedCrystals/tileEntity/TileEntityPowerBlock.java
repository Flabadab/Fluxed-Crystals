package fluxedCrystals.tileEntity;

import java.util.EnumSet;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import fluxedCrystals.api.CrystalBase;
import fluxedCrystals.blocks.crystal.BlockCrystal;

/**
 * Created by Jared on 11/2/2014.
 */
public class TileEntityPowerBlock extends TileEnergyBase {

	private TileEntityManagerBlock manager;
	@Getter
	@Setter
	public ItemStack[] managerUpgrades = new ItemStack[3];

	public TileEntityPowerBlock() {
		super(10000);
	}

	public void updateEntity() {
		super.updateEntity();
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

	@Override
	public EnumSet<ForgeDirection> getValidOutputs() {
		EnumSet<ForgeDirection> list = EnumSet.noneOf(ForgeDirection.class);
		// list.add(ForgeDirection.NORTH);
		// list.add(ForgeDirection.EAST);
		// list.add(ForgeDirection.SOUTH);
		// list.add(ForgeDirection.WEST);
		return list;
	}

	@Override
	public EnumSet<ForgeDirection> getValidInputs() {
		EnumSet<ForgeDirection> list = EnumSet.noneOf(ForgeDirection.class);
		return list;
	}
}
