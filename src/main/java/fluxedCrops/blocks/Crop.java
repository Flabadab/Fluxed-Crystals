package fluxedCrops.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fluxedCrops.ModProps;
import fluxedCrops.api.CropBase;
import fluxedCrops.tileEntity.TileEntityCrop;

public class Crop extends CropBase implements ITileEntityProvider {
	private IIcon[] icons;
	private String material;

	public Crop() {
		
	}

	public boolean growCrop(World world, int x, int y, int z, Random rand) {
		if (world.getBlockLightValue(x, y + 1, z) >= 9) {
			int l = world.getBlockMetadata(x, y, z);

			if (l < 7) {
				float f = this.func_149864_n(world, x, y, z);

				if (rand.nextInt((int) (25.0F / f) + 1) == 0) {
					++l;
					world.setBlockMetadataWithNotify(x, y, z, l, 2);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random rand) {
	}

	private float func_149864_n(World world, int x, int y, int z) {
		float f = 1.0F;

		for (int l = x - 1; l <= x + 1; ++l) {
			for (int i1 = z - 1; i1 <= z + 1; ++i1) {
				float f1 = 0.0F;

				if (world.getBlock(l, y - 1, i1).canSustainPlant(world, l, y - 1, i1, ForgeDirection.UP, this)) {
					f1 = 2.0F;
				}

				f += f1;
			}
		}

		return f;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		this.icons = new IIcon[8];

		for (int i = 0; i < this.icons.length; ++i) {
			this.icons[i] = p_149651_1_.registerIcon(ModProps.modid + ":crop_stage_" + i);
		}
		this.icons[7] = p_149651_1_.registerIcon(ModProps.modid + ":" + material + "_stage_" + 7);
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta < 0 || meta > 7) {
			meta = 7;
		}

		return this.icons[meta];
	}

	/**
	 * Checks to see if its valid to put this block at the specified
	 * coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) == FCBlocks.powerBlock;
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
