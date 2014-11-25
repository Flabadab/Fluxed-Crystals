package fluxedCrops;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import fluxedCrops.blocks.FCBlocks;

public class CreativeTabFluxedCrops extends CreativeTabs {

	public CreativeTabFluxedCrops() {
		super("Fluxed-Crops");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(FCBlocks.powerBlock);
	}

}
