package fluxedCrystals.nei;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import fluxedCrystals.ModProps;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.RecipeSeedInfuser;

public class InfuserRecipeHandler extends TemplateRecipeHandler {

	@Override
	public String getGuiTexture() {
		return ModProps.modid + ":textures/gui/SeedInfuser.png";
	}

	@Override
	public String getRecipeName() {
		return "Seed Infuser";
	}

	@Override
	public int recipiesPerPage() {
		return 1;
	}

	private final ResourceLocation texture = new ResourceLocation(getGuiTexture());

	@Override
	public void drawBackground(int recipe) {
		GL11.glScalef(0.70f, 0.65f, 0.59f);

		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(0, 0, 0, 0, 52, 55);
		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void drawForeground(int recipe) {
		super.drawForeground(recipe);
	}

	@Override
	public void drawExtras(int recipe) {
		CachedInfusionRecipe r = (CachedInfusionRecipe) arecipes.get(recipe);
		int coords2[] = { 0, 0 };
		GL11.glScalef(.08f, .08f, .08f);
		GL11.glEnable(GL11.GL_BLEND);

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glScalef(15.625f, 15.625f, 15.625f);
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		System.out.println("hithere");
		List<RecipeSeedInfuser> recipes = RecipeRegistry.getSeedRecipes();

		for (int j = 0; j < recipes.size(); j++) {
			RecipeSeedInfuser recipe = recipes.get(j);
			if (recipe.getOutput().isItemEqual(result)) {
				this.arecipes.add(new CachedInfusionRecipe(recipe));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {

		List<RecipeSeedInfuser> recipes = RecipeRegistry.getSeedRecipes();

		for (int j = 0; j < recipes.size(); j++) {
			RecipeSeedInfuser recipe = recipes.get(j);
			if (recipe.getInput().isItemEqual(ingredient) || recipe.getIngredient().isItemEqual(ingredient) || recipe.getOutput().isItemEqual(ingredient)) {
//				if (checkDupe(recipe))
					this.arecipes.add(new CachedInfusionRecipe(recipe));
			}
		}
	}

//	private boolean checkDupe(RecipeSeedInfuser recipe) {
//		for (Object o : this.arecipes.toArray()) {
//			if (o instanceof CachedInfusionRecipe) {
//				CachedInfusionRecipe r = (CachedInfusionRecipe) o;
//				if (r.recipe.getInput() == recipe.getInput()) {
//					if (r.recipe.getOutput().isItemEqual(recipe.getOutput())) {
//						return false;
//					}
//				}
//			}
//		}
//		return true;
//	}

	public class CachedInfusionRecipe extends CachedRecipe {

		private PositionedStack output;
		private PositionedStack inputs;
		private List<PositionedStack> other = new ArrayList<PositionedStack>();

		public RecipeSeedInfuser recipe;

		public CachedInfusionRecipe(RecipeSeedInfuser recipe) {
			this.output = new PositionedStack(recipe.getOutput(), 75, 53);
			this.recipe = recipe;
			this.inputs = new PositionedStack(recipe.getInput(), 75, 98);
			this.other.add(new PositionedStack(recipe.getIngredient(), 1, 1));

		}

		@Override
		public PositionedStack getResult() {
			return this.output;
		}

		@Override
		public PositionedStack getIngredient() {
			return this.inputs;
		}

		@Override
		public List<PositionedStack> getOtherStacks() {
			return other;
		}
	}

	@Override
	public String getOverlayIdentifier() {
		return "seedInfuser";
	}

}
