package fluxedCrystals.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import fluxedCrystals.tileEntity.TileEntityPowerBlock;

public class BlockPowerBlock extends Block implements ITileEntityProvider {

	protected BlockPowerBlock() {
		super(Material.grass);
		this.setHardness(2.0F);
	}

	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPowerBlock();
	}

	public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
		TileEntityPowerBlock power = (TileEntityPowerBlock) world.getTileEntity(x, y, z);
		power.getManager().getPowerBlocks().remove(power);
		power.setManager(null);
	}

}
