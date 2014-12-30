package fluxedCrystals.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockIce;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.ModProps;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.tileEntity.TileEntityGlass;
import fluxedCrystals.tileEntity.TileEntityRoughChunk;

public class BlockRoughChunk extends Block implements ITileEntityProvider {

	public BlockRoughChunk() {
		super(Material.glass);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		// TODO Rough shards
		items.add(new ItemStack(FCItems.shard, 1, ((TileEntityGlass) world.getTileEntity(x, y, z)).getIndex()));
		return items;
	}

	public int getRenderType() {
		return FluxedCrystals.chunkRenderID;
	}

	public void setData(ItemStack shard, IBlockAccess world, int x, int y, int z) {
		TileEntityRoughChunk tile = (TileEntityRoughChunk) world.getTileEntity(x, y, z);
		if (tile != null) {
			tile.init(shard.getItemDamage());
		}
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random p_149745_1_) {
		return 0;
	}

	/**
	 * Returns which pass should this block be rendered on. 0 for solids and 1
	 * for alpha
	 */
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 0;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock() {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityRoughChunk();
	}
}
