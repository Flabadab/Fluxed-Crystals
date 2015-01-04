package fluxedCrystals.api.recipe;

import java.util.ArrayList;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeGemRefiner {

	private ItemStack input;
	private ItemStack output;
	private int inputAmount;

	public RecipeGemRefiner(ItemStack input, ItemStack output, int inputAmount) {
		this.input = input;
		this.output = output;
		this.inputAmount = inputAmount;
	}

	public boolean matches(ItemStack stack) {
		int[] ids = OreDictionary.getOreIDs(stack);
		for (int id : ids) {
			String name = OreDictionary.getOreName(id);
			if (matches(name)) {
				return true;
			}
		}
		return stack != null && OreDictionary.itemMatches(stack, input, false);
	}

	public boolean matches(String oreDict) {
		ArrayList<ItemStack> stacks = OreDictionary.getOres(oreDict);
		for (ItemStack stack : stacks) {
			if (OreDictionary.itemMatches(stack, input, false)) {
				return true;
			}
		}
		return false;
	}

	public boolean matchesExact(ItemStack stack) {
		if (stack != null)
			return input.isItemEqual(stack);
		return false;
	}

	public ItemStack getInput() {
		ItemStack stack = input.copy();
		// stack.stackSize = getInputamount();
		return stack;
	}

	public ItemStack getOutput() {
		return output.copy();
	}

	public int getInputamount() {
		return inputAmount;
	}
}
