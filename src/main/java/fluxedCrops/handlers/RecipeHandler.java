package fluxedCrops.handlers;

import tterrag.core.common.OreDict;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.blocks.FCBlocks;
import fluxedCrops.config.ConfigProps;
import fluxedCrops.items.FCItems;

public class RecipeHandler {

	public static void init() {
		registerRecipes();
	}

	private static void registerRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(FCItems.universalSeed, 2), "sss", "rar", "sss", 's', Items.wheat_seeds, 'r', Blocks.redstone_block, 'a', new ItemStack(Items.potionitem, 1, 16));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.upgradeNight, " c ", "cuc", " c ", 'c', Items.clock, 'u', FCItems.universalSeed));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.upgradeEffeciency, " c ", "cuc", " c ", 'c', Blocks.coal_block, 'u', FCItems.universalSeed));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.upgradeAutomation, " p ", "sus", " p ", 'p', Blocks.piston, 's', Blocks.sticky_piston, 'u', FCItems.universalSeed));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.upgradeSpeed, " s ", "sus", " s ", 's', Items.sugar, 'u', FCItems.universalSeed));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.powerBlock, "sis", "aea", "sis", 's', Blocks.soul_sand, 'i', "ingotIron", 'a', Blocks.sand, 'e', Items.wheat_seeds));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.managerBlock, "tie", "srs", "eit", 's', Blocks.soul_sand, 'i', "ingotIron", 't', Blocks.stone, 'e', Items.wheat_seeds, 'r', Items.redstone));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.seedInfuser, "gdi", "sus", "idg", 's', Blocks.soul_sand, 'i', "ingotIron", 'g', Items.gold_ingot, 'd', Items.diamond, 'u', FCItems.universalSeed));

		for (int i = 0; i < RecipeRegistry.getSeedCropRecipes().size(); i++) {
			if (ConfigProps.shard3x3) {
				GameRegistry.addShapedRecipe(RecipeRegistry.getIngredient(i), "sss", "sss", "sss", 's', new ItemStack(FCItems.shard, 1, i));
			} else {
				GameRegistry.addShapelessRecipe(RecipeRegistry.getIngredient(i), new ItemStack(FCItems.shard, 1, i), new ItemStack(FCItems.shard, 1, i), new ItemStack(FCItems.shard, 1, i), new ItemStack(FCItems.shard, 1, i));
			}
		}
	}
}
