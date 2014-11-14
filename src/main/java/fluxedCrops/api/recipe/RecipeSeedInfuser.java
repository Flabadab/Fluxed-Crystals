package fluxedCrops.api.recipe;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeSeedInfuser {

	public ItemStack input;
	public String cropBlock;

	public RecipeSeedInfuser(ItemStack input, String cropBlock) {
		this.input = input;
		this.cropBlock = cropBlock;
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
		return OreDictionary.getOreName(OreDictionary.getOreID(stack)).equals(getInput());
	}

	public ItemStack getInput() {
		return input;
	}

	public String getOutput() {
		return cropBlock;
	}

}
