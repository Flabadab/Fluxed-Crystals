package fluxedCrops.blocks.crops;

import java.util.ArrayList;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.item.EntityItem;
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
		for (ItemStack stack : getDrop(world, x, y, z, metadata, (TileEntityCrop) world.getTileEntity(x, y, z))) {
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, stack));
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

	public ArrayList<ItemStack> getDrop(World world, int x, int y, int z, int metadata, TileEntityCrop tile) {
		for (SeedCropRecipe recipe : RecipeRegistry.getSeedCropRecipes()) {
			if (recipe.matches(this)) {
				ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
				ret.add(recipe.getSeed());
				if (metadata >= 7)
					ret.add(recipe.getDrop());

				return ret;
			}
		}
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCrop();
	}
}
