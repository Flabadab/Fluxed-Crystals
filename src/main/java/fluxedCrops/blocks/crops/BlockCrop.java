package fluxedCrops.blocks.crops;

import java.util.ArrayList;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fluxedCrops.api.CropBase;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.recipe.SeedCropRecipe;
import fluxedCrops.tileEntity.TileEntityCrop;

public class BlockCrop extends CropBase implements ITileEntityProvider {

	public BlockCrop() {
	}

	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata) {

		for (SeedCropRecipe recipe : RecipeRegistry.getSeedCropRecipes()) {
			if (recipe.matches(((TileEntityCrop) world.getTileEntity(x, y, z)).getSeed().copy())) {
				if (metadata >= 7)
					world.spawnEntityInWorld(new EntityItem(world, x, y, z, ((TileEntityCrop) world.getTileEntity(x, y, z)).getDrop().copy()));
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, ((TileEntityCrop) world.getTileEntity(x, y, z)).getSeed().copy()));
			}
		}
	}

	public void setOthers(ItemStack seed, ItemStack drop, IBlockAccess world, int x, int y, int z) {
		TileEntityCrop tile = (TileEntityCrop) world.getTileEntity(x, y + 1, z);
		if (tile != null) {
			tile.setSeed(seed);
			tile.setDrop(drop);
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
