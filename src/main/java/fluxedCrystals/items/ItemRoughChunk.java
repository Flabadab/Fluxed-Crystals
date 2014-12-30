package fluxedCrystals.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
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

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		if (world.getBlock(x, y, z) != null) {
			world.setBlock(x, y + 1, z, FCBlocks.roughChunk);
			((BlockRoughChunk) world.getBlock(x, y + 1, z)).setData(stack, world, x, y, z);
			player.inventory.decrStackSize(player.inventory.currentItem, 1);
			return true;
		}
		return false;
	}
}
