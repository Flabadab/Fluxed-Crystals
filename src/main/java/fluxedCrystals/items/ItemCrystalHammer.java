package fluxedCrystals.items;

import fluxedCrystals.blocks.BlockGlass;
import fluxedCrystals.blocks.FCBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemCrystalHammer extends Item {
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 250;
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {

		if (!world.isRemote)
			if (world.getBlock(x, y, z) == FCBlocks.infusedGlass) {
				int newMeta = world.getBlockMetadata(x, y, z) + 1;
				if (newMeta >= ((BlockGlass) world.getBlock(x, y, z)).getIcons().length) {
					newMeta = 0;
				}
				world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);
				return true;
			}
		return false;
	}
}
