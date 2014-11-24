package fluxedCrops.handlers;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.items.FCItems;

public class RecipeHandler {

	public static void init() {
		registerRecipes();
	}

	private static void registerRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(FCItems.universalSeed), "sss", "rar", "sss", 's', Items.wheat_seeds, 'r', Blocks.redstone_block, 'a', new ItemStack(Items.potionitem, 1, 16));
	}

}
