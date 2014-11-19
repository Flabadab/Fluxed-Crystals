package fluxedCrops.items.seeds;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.SeedBase;

/**
 * Created by Jared on 11/2/2014.
 */
public class ItemSeed extends SeedBase {

	public ItemSeed() {
		setUnlocalizedName("fluxedcrops.seed");
		setTextureName("fluxedcrops:seed");
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
		int numSeeds = RecipeRegistry.getSeedCropRecipes().size();
		for (int i = 0; i < numSeeds; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return String.format(StatCollector.translateToLocal(getUnlocalizedName() + ".name"), RecipeRegistry.getName(stack.getItemDamage()));
	}
}
