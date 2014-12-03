package fluxedCrystals;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import fluxedCrystals.blocks.FCBlocks;


public class CreativeTabFluxedCrystals extends CreativeTabs {

        public CreativeTabFluxedCrystals() {
                super("Fluxed-Crystals");
        }

        @Override
        public Item getTabIconItem() {
                return Item.getItemFromBlock(FCBlocks.powerBlock);
        }

}
