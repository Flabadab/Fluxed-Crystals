package fluxedCrystals.handlers;

import tterrag.core.common.OreDict;
import vazkii.botania.api.BotaniaAPI;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.ChunkDataEvent.Load;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.SeedCropRecipe;
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
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.crystalHammer, "  c", " s ", "s  ", 's', Items.stick, 'u', FCItems.universalSeed));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheWood, " ww", "s  ", " s ", 's', Items.stick, 'w', Blocks.planks).setMirrored(true));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheStone, " ww", "s  ", " s ", 's', Items.stick, 'w', Blocks.cobblestone).setMirrored(true));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheIron, " ww", "s  ", " s ", 's', Items.stick, 'w', Items.iron_ingot).setMirrored(true));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheGold, " ww", "s  ", " s ", 's', Items.stick, 'w', Items.gold_ingot).setMirrored(true));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheDiamond, " ww", "s  ", " s ", 's', Items.stick, 'w', Items.diamond).setMirrored(true));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.powerBlock, "sis", "aea", "sis", 's', Blocks.soul_sand, 'i', "ingotIron", 'a', Blocks.sand, 'e', Items.wheat_seeds));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.managerBlock, "tie", "srs", "eit", 's', Blocks.soul_sand, 'i', "ingotIron", 't', Blocks.stone, 'e', Items.wheat_seeds, 'r', Items.redstone));
		if (Loader.isModLoaded("AWWayofTime"))
			GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.managerBlood, "tie", "srs", "eit", 's', Blocks.soul_sand, 'i', "ingotIron", 't', Blocks.stone, 'e', GameRegistry.findItem("AWWayofTime", "blankSpell"), 'r', Items.redstone));
		if (Loader.isModLoaded("Botania"))
			GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.managerMana, "tie", "srs", "eit", 's', Blocks.soul_sand, 'i', "ingotIron", 't', Blocks.stone, 'e', new ItemStack(GameRegistry.findItem("Botania", "manaResource"), 1, 2), 'r', Items.redstone));
		if (Loader.isModLoaded("IC2"))
			GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.managerIndustrial, "tie", "srs", "eit", 's', Blocks.soul_sand, 'i', "ingotIron", 't', Blocks.stone, 'e', new ItemStack(GameRegistry.findBlock("IC2", "blockElectric")), 'r', Items.redstone));
		if (Loader.isModLoaded("Thaumcraft"))
			GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.managerThaumic, "tie", "srs", "eit", 's', Blocks.soul_sand, 'i', "ingotIron", 't', Blocks.stone, 'e', new ItemStack(GameRegistry.findBlock("Thaumcraft", "blockMetalDevice"), 1, 8), 'r', Items.redstone));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.seedInfuser, "gdi", "sus", "idg", 's', Blocks.soul_sand, 'i', "ingotIron", 'g', Items.gold_ingot, 'd', Items.diamond, 'u', FCItems.universalSeed));

		for (int i = 0; i < RecipeRegistry.getNumSeedRecipes(); i++) {
			if (ConfigProps.shard3x3) {
				GameRegistry.addShapedRecipe(RecipeRegistry.getIngredient(i), "sss", "sss", "sss", 's', new ItemStack(FCItems.shard, 1, i));
			} else {
				GameRegistry.addShapelessRecipe(RecipeRegistry.getIngredient(i), new ItemStack(FCItems.shard, 1, i), new ItemStack(FCItems.shard, 1, i), new ItemStack(FCItems.shard, 1, i), new ItemStack(FCItems.shard, 1, i));
			}
		}
	}
}
