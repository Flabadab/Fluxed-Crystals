package fluxedCrops.blocks.crops;

import java.util.ArrayList;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fluxedCrops.api.CropBase;
import fluxedCrops.tileEntity.TileEntityCrop;

public class BlockCrop extends CropBase implements ITileEntityProvider {

	public BlockCrop() {

	}

	public void setOthers(ItemStack seed, ItemStack drop, IBlockAccess world, int x, int y, int z) {
		TileEntityCrop tile = (TileEntityCrop) world.getTileEntity(x, y, z);
		if (tile != null) {
			tile.setSeed(seed);
			tile.setDrop(drop);
			System.out.println(seed + " " + drop);
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		world.setBlock(x, y + 1, z, Blocks.diamond_ore);

		TileEntityCrop tile = (TileEntityCrop) world.getTileEntity(x, y, z);
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		if (tile != null) {
			ret.add(tile.getSeed());
			if (metadata >= 7)
				ret.add(tile.getDrop());
		}
		return ret;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCrop();
	}
}
