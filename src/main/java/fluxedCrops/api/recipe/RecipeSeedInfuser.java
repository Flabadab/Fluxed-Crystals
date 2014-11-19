package fluxedCrops.api.recipe;

import java.util.ArrayList;

import lombok.Getter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

@Getter
public class RecipeSeedInfuser {

	private ItemStack input;
	private ItemStack output;

	public RecipeSeedInfuser(ItemStack input, ItemStack output) {
		this.input = input;
		this.output = output;
	}

	public boolean matches(ItemStack stack) {
		return input.isItemEqual(stack);
	}

	public boolean matches(String oreDict) {
		ArrayList<ItemStack> stacks = OreDictionary.getOres(oreDict);
		for (ItemStack stack : stacks) {
			return input.isItemEqual(stack);
		}
		return false;
	}

	public boolean matchesInput(ItemStack stack) {
		int[] ids = OreDictionary.getOreIDs(stack);
		for (int id : ids) {
			if (OreDictionary.getOreName(id).equals(getInput())) {
				return true;
			}
		}
		return false;
	}
}
