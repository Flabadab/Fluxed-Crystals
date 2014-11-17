package fluxedCrops.blocks.crops;

import java.util.ArrayList;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fluxedCrops.api.CropBase;
import fluxedCrops.items.FCItems;
import fluxedCrops.tileEntity.TileEntityCrop;

public class BlockCrop extends CropBase implements ITileEntityProvider {

	public BlockCrop() {
	}

	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata) {

		TileEntityCrop crop = (TileEntityCrop) world.getTileEntity(x, y, z);
		dropBlockAsItem(world, x, y, z, crop.getDrop());
		dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.seed, 1, crop.getIndex()));
	}

	public void setData(ItemStack seed, IBlockAccess world, int x, int y, int z) {
		TileEntityCrop tile = (TileEntityCrop) world.getTileEntity(x, y + 1, z);
		if (tile != null) {
			tile.init(seed.getItemDamage());
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCrop();
	}
}
