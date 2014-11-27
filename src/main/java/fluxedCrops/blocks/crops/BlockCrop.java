package fluxedCrops.blocks.crops;

import java.awt.Color;
import java.util.ArrayList;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fluxedCrops.api.CropBase;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.config.ConfigProps;
import fluxedCrops.items.FCItems;
import fluxedCrops.tileEntity.TileEntityCrop;

public class BlockCrop extends CropBase implements ITileEntityProvider {

	public BlockCrop() {
	}

	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata) {
		TileEntityCrop crop = (TileEntityCrop) world.getTileEntity(x, y, z);
		dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.seed, 1, crop.getIndex()));
		if (metadata >= 7) {
			if (ConfigProps.shardDrop) {
				dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.shard, crop.getDropAmount(), crop.getIndex()));
			} else {
				dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.seed, crop.getDropAmount(), crop.getIndex()));
			}
		}

	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ) {
		if (world.getBlockMetadata(x, y, z) >= 7) {
			TileEntityCrop crop = (TileEntityCrop) world.getTileEntity(x, y, z);
			if (world.getBlockMetadata(x, y, z) >= 7) {
				if (ConfigProps.shardDrop) {
					dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.shard, crop.getDropAmount(), crop.getIndex()));
				} else {
					dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.seed, crop.getDropAmount(), crop.getIndex()));
					}
			}
			world.setBlockMetadataWithNotify(x, y, z, 0, 3);

			return true;
		}
		return false;
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
