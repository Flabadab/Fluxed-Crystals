package fluxedCrops.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import fluxedCrops.blocks.FCBlocks;
import fluxedCrops.blocks.crops.BlockCrop;

/**
 * Created by Jared on 11/2/2014.
 */
public abstract class SeedBase extends Item implements ISeed {
	
	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		ItemStack seeds = stack.copy();
		seeds.stackSize = 1;
		if (world.getBlock(x, y, z) == FCBlocks.powerBlock) {
			if (hitY == 1.0F) {
				world.setBlock(x, y + 1, z, FCBlocks.crop);
				((BlockCrop) world.getBlock(x, y + 1, z)).setData(stack, world, x, y, z);
				player.inventory.decrStackSize(player.inventory.currentItem, 1);
				return true;
			}
		}
		return false;
	}
}
