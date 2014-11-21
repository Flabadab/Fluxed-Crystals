package fluxedCrops;

import fluxedCrops.blocks.FCBlocks;
import tterrag.core.common.util.CreativeTabsCustom;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabFluxedCrops extends CreativeTabs {

	public CreativeTabFluxedCrops() {
		super("Fluxed-Crops");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(FCBlocks.powerBlock);
	}

}
