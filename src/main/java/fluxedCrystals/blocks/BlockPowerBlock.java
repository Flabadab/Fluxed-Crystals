package fluxedCrystals.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import fluxedCrystals.ModProps;
import fluxedCrystals.tileEntity.TileEntityPowerBlock;

public class BlockPowerBlock extends Block implements ITileEntityProvider {


	protected BlockPowerBlock() {
		super(Material.grass);
		this.setHardness(2.0F);
	}


	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Blocks.dirt));
		return stack;
	}

	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPowerBlock();
	}

	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		TileEntityPowerBlock power = (TileEntityPowerBlock) world.getTileEntity(x, y, z);
		power.setManager(null);
	}

}
