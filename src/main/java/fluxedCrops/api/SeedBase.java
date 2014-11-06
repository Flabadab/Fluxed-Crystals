package fluxedCrops.api;

import fluxedCrops.blocks.FCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

/**
 * Created by Jared on 11/2/2014.
 */
public abstract class SeedBase extends Item implements ISeed, IPlantable {

	private Block crop;

	public SeedBase(Block crop) {
		this.crop = crop;
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {

		if (world.getBlock(x, y, z) == FCBlocks.powerBlock) {
			if (hitY == 1.0F) {
				world.setBlock(x, y + 1, z, crop);
				--stack.stackSize;
				return true;
			}
		}
		return false;
	}
}
