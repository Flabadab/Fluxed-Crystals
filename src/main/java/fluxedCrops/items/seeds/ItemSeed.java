package fluxedCrops.items.seeds;

import fluxedCrops.api.SeedBase;
import fluxedCrops.blocks.FCBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

/**
 * Created by Jared on 11/2/2014.
 */
public class ItemSeed extends SeedBase {

	public Block crop;
	private int color;

	public ItemSeed(Block crop, ItemStack drop) {
		super(crop, drop);
		this.crop = crop;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getRenderPasses(int metadata) {
		return 1;
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return color;
	}

	@Override
	public Block getCrop() {
		return crop;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return crop;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return 0;
	}
}
