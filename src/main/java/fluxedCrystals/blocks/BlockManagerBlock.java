package fluxedCrystals.blocks;

import java.util.ArrayList;
import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.compat.waila.IWailaInfo;
import fluxedCrystals.tileEntity.TileEntityGemRefiner;
import fluxedCrystals.tileEntity.TileEntityManagerBlock;

/**
 * Created by Jared on 11/3/2014.
 */
public class BlockManagerBlock extends Block implements ITileEntityProvider {
	protected BlockManagerBlock() {
		super(Material.anvil);
		this.setHardness(2.0F);
		setHarvestLevel("pickaxe", 2);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float par7, float par8, float par9) {
		if (!world.isRemote)
			player.openGui(FluxedCrystals.instance, 0, world, x, y, z);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityManagerBlock();
	}

	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		TileEntityManagerBlock tile = (TileEntityManagerBlock) world.getTileEntity(x, y, z);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(new ItemStack(this));
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				if (tile.getStackInSlot(i) != null)
					items.add(tile.getStackInSlot(i));
			}
		}
		return items;
	}
}
