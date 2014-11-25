package fluxedCrops.handlers;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.config.ConfigProps;
import fluxedCrops.items.FCItems;

public class RecipeHandler {

	public static void init() {
		registerRecipes();
	}

	private static void registerRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(FCItems.universalSeed), "sss", "rar", "sss", 's', Items.wheat_seeds, 'r', Blocks.redstone_block, 'a', new ItemStack(Items.potionitem, 1, 16));

		for (int i = 0; i < RecipeRegistry.getSeedCropRecipes().size(); i++) {
			if (ConfigProps.shard3x3) {
				GameRegistry.addShapedRecipe(RecipeRegistry.getDrop(i), "sss", "sss", "sss", 's', new ItemStack(FCItems.shard, 1, i));
			} else {
				GameRegistry.addShapelessRecipe(RecipeRegistry.getDrop(i), new ItemStack(FCItems.shard, 1, i), new ItemStack(FCItems.shard, 1, i), new ItemStack(FCItems.shard, 1, i), new ItemStack(FCItems.shard, 1, i));
			}
		}
	}

}
