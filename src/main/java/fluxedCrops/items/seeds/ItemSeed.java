package fluxedCrops.items.seeds;

import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.SeedBase;
import fluxedCrops.api.recipe.SeedCropRecipe;
import fluxedCrops.blocks.FCBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

/**
 * Created by Jared on 11/2/2014.
 */
public class ItemSeed extends SeedBase {

	private int color;

	public ItemSeed() {
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
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		for(SeedCropRecipe recipe : RecipeRegistry.getSeedCropRecipes()){
			if(recipe.matches(new ItemStack(this))){
				return recipe.getCrop();
			}
		}
		
		return null;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return 0;
	}
}
