package fluxedCrystals.handlers;

import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.RecipeGemCutter;
import fluxedCrystals.api.recipe.RecipeGemRefiner;
import fluxedCrystals.api.recipe.RecipeSeedInfuser;
import fluxedCrystals.api.recipe.SeedCrystalRecipe;
import fluxedCrystals.blocks.FCBlocks;
import fluxedCrystals.config.ConfigProps;
import fluxedCrystals.items.FCItems;

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
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.crystalHammer, "  c", " s ", "s  ", 's', Items.stick, 'c', FCItems.universalSeed));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheWood, " ww", "s  ", " s ", 's', Items.stick, 'w', Blocks.planks).setMirrored(true));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheStone, " ww", "s  ", " s ", 's', Items.stick, 'w', Blocks.cobblestone).setMirrored(true));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheIron, " ww", "s  ", " s ", 's', Items.stick, 'w', Items.iron_ingot).setMirrored(true));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheGold, " ww", "s  ", " s ", 's', Items.stick, 'w', Items.gold_ingot).setMirrored(true));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheDiamond, " ww", "s  ", " s ", 's', Items.stick, 'w', Items.diamond).setMirrored(true));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.gemCutter, "wwi", "wii", "wi ", 'w', Blocks.planks, 'i', Items.iron_ingot).setMirrored(true));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.powerBlock, "sis", "aea", "sis", 's', Blocks.soul_sand, 'i', "ingotIron", 'a', Blocks.sand, 'e', Items.wheat_seeds));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.managerBlock, "tie", "srs", "eit", 's', Blocks.soul_sand, 'i', "ingotIron", 't', Blocks.stone, 'e', Items.wheat_seeds, 'r', Items.redstone));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.seedInfuser, "gdi", "sus", "idg", 's', Blocks.soul_sand, 'i', "ingotIron", 'g', Items.gold_ingot, 'd', Items.diamond, 'u', FCItems.universalSeed));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.gemCutter, "gdi", "sus", "idg", 's', Blocks.soul_sand, 'i', "ingotIron", 'g', Items.gold_ingot, 'd', Items.diamond, 'u', FCItems.gemCutter));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.gemRefiner, "gdi", "sus", "idg", 's', Blocks.soul_sand, 'i', "ingotIron", 'g', Items.gold_ingot, 'd', Items.diamond, 'u', FCItems.upgradeAutomation));
		List<SeedCrystalRecipe> recipes = RecipeRegistry.getSeedCropRecipes();
		for (int i = 0; i < RecipeRegistry.getNumSeedRecipes(); i++) {
			//			SeedCrystalRecipe r = recipes.get(i);
			if (ConfigProps.normalShardRecipes) {
				if (ConfigProps.shard3x3) {
					GameRegistry.addShapedRecipe(RecipeRegistry.getIngredient(i), "sss", "sss", "sss", 's', new ItemStack(FCItems.shard, 1, i));
				} else {
					GameRegistry.addShapelessRecipe(RecipeRegistry.getIngredient(i), new ItemStack(FCItems.shard, 1, i), new ItemStack(FCItems.shard, 1, i), new ItemStack(FCItems.shard, 1, i), new ItemStack(FCItems.shard, 1, i));
				}
			}
//			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FCItems.shard, 1, i), new ItemStack(FCItems.gemCutter), new ItemStack(FCItems.roughShard, 1, i)));
//			RecipeRegistry.registerGemRefinerRecipe(new RecipeGemRefiner(new ItemStack(FCItems.shard, 1, i), r.getIngredient(), r.getRefinerAmount()));
//			RecipeRegistry.registerGemCutterRecipe(new RecipeGemCutter(new ItemStack(FCItems.roughShard, 1, i), new ItemStack(FCItems.shard, 1, i), 1));
//			RecipeRegistry.registerSeedInfuserRecipe(new RecipeSeedInfuser(new ItemStack(FCItems.universalSeed), r.getIngredient(), new ItemStack(FCItems.seed, 1, i), RecipeRegistry.getIngredientAmount(i)));

		}

	}
}
