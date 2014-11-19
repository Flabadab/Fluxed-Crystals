package fluxedCrops.api.recipe;

import java.util.ArrayList;

import lombok.Getter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeSeedInfuser {

	private ItemStack input;
	private ItemStack output;

	public RecipeSeedInfuser(ItemStack input, ItemStack output) {
		this.input = input;
		this.output = output;
	}

	public boolean matches(ItemStack stack) {
		int[] ids = OreDictionary.getOreIDs(stack);
		for (int id : ids) {
			String name = OreDictionary.getOreName(id);
			if (matches(name)) {
				return true;
			}
		}
		return false;
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
	
	public ItemStack getInput() {
		return input.copy();
	}
	
	public ItemStack getOutput() {
		return output.copy();
	}
}
