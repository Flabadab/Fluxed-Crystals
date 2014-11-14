package fluxedCrops.blocks.crops;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import fluxedCrops.api.CropBase;
import fluxedCrops.items.FCItems;
import fluxedCrops.tileEntity.TileEntityCrop;
import fluxedCrops.tileEntity.TileEntitySeedInfuser;

public class BlockCrop extends CropBase implements ITileEntityProvider {

	protected ItemStack seed;
	protected ItemStack drop;

	public BlockCrop() {
	}


	public void setSeed(ItemStack seed) {
		this.seed = seed;
	}

	public void setDrop(ItemStack drop) {
		this.drop = drop;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		TileEntityCrop tile = (TileEntityCrop) world.getTileEntity(x, y, z);

		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(tile.getSeed());
		if (metadata >= 7)
			ret.add(tile.getDrop());

		return ret;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCrop(seed, drop);
	}
}
