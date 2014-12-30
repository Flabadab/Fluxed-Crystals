package fluxedCrystals.items;

import java.util.List;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import fluxedCrystals.ModProps;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.SeedBase;
import fluxedCrystals.blocks.BlockGlass;
import fluxedCrystals.blocks.BlockRoughChunk;
import fluxedCrystals.blocks.FCBlocks;
import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.tileEntity.TileEntityGlass;

/**
 * Created by Jared on 11/2/2014.
 */
public class ItemRoughChunk extends Item {

	public ItemRoughChunk() {
		setUnlocalizedName(ModProps.modid + ".roughChunk");
		setTextureName(ModProps.modid + ":roughChunk");
		setHasSubtypes(true);
	}

	public int getRenderPasses(int metadata) {
		return 1;
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return RecipeRegistry.getColor(par1ItemStack.getItemDamage());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List list) {
		int numSeeds = RecipeRegistry.getNumSeedRecipes();
		for (int i = 0; i < numSeeds; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return String.format(StatCollector.translateToLocal(getUnlocalizedName() + ".name"), RecipeRegistry.getName(stack.getItemDamage()));
	}

	// @Override
	// public boolean onItemUse(ItemStack stack, EntityPlayer player, World
	// world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ)
	// {
	//
	// if (world.getBlock(x, y, z) != null || world.getBlock(x, y,
	// z).isReplaceable(world, x, y, z)) {
	// world.setBlock(x, y + 1, z, FCBlocks.roughChunk);
	// ((BlockRoughChunk) world.getBlock(x, y, z)).setData(stack, world, x, y,
	// z);
	// player.inventory.decrStackSize(player.inventory.currentItem, 1);
	// return true;
	// }
	// return false;
	// }
	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		boolean flag = true;
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(p_77659_2_, p_77659_3_, flag);

		if (movingobjectposition == null) {
			return p_77659_1_;
		} else {

			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!p_77659_2_.canMineBlock(p_77659_3_, i, j, k)) {
					return p_77659_1_;
				}

				if (movingobjectposition.sideHit == 0) {
					--j;
				}

				if (movingobjectposition.sideHit == 1) {
					++j;
				}

				if (movingobjectposition.sideHit == 2) {
					--k;
				}

				if (movingobjectposition.sideHit == 3) {
					++k;
				}

				if (movingobjectposition.sideHit == 4) {
					--i;
				}

				if (movingobjectposition.sideHit == 5) {
					++i;
				}

				p_77659_2_.setBlock(i, j, k, FCBlocks.roughChunk);
				((BlockRoughChunk) p_77659_2_.getBlock(i, j, k)).setData(p_77659_1_, p_77659_2_, i, j, k);
				ItemStack stack = p_77659_1_.copy();
				stack.stackSize--;
				return stack;
			}
		}

		return p_77659_1_;
	}

}
