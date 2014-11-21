package fluxedCrops.api.recipe;

import lombok.Getter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import tterrag.core.common.OreDict;

@Getter
public class SeedCropRecipe {

	private String name;
	private ItemStack drop;
	private int color;
	private ItemStack ingredient;
	private Aspect aspect;

	public SeedCropRecipe(String name, ItemStack drop, int color, ItemStack ingredient) {
		this.name = name;
		this.drop = drop;
		this.color = color;
		this.ingredient = ingredient;
	}

	public SeedCropRecipe(String name, Aspect aspect) {
		ItemStack drop = ItemApi.getItem("itemWispEssence", 0);
		ItemStack ingredient = ItemApi.getItem("itemWispEssence", 0);

		if (drop.getItem() instanceof IEssentiaContainerItem) {
			((IEssentiaContainerItem) drop.getItem()).setAspects(drop, new AspectList().add(aspect, 2));
		}
		if (ingredient.getItem() instanceof IEssentiaContainerItem) {
			((IEssentiaContainerItem) ingredient.getItem()).setAspects(ingredient, new AspectList().add(aspect, 2));
		}
		this.name = name;
		this.drop = drop;
		this.color = aspect.getColor();
		this.ingredient = ingredient;
		this.aspect = aspect;
	}

}
