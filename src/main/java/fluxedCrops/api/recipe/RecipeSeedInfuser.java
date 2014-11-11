package fluxedCrops.api.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeSeedInfuser {

	public ItemStack input;
	public ItemStack output;

	public RecipeSeedInfuser(ItemStack input, ItemStack output) {
		this.input = input;
		this.output = output;
	}

	public boolean matches(ItemStack stack) {
		if (input.isItemEqual(stack)) {
			return true;
		}
		return false;
	}

	public boolean matches(String oreDict) {
		ArrayList<ItemStack> stacks = OreDictionary.getOres(oreDict);
		for (ItemStack stack : stacks) {
			if (input.isItemEqual(stack)) {
				return true;
			}
		}
		return false;
	}

	public ItemStack getInput() {
		return input;
	}

	public ItemStack getOutput() {
		return output;
	}
	
}
