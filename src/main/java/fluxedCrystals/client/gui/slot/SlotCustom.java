package fluxedCrystals.client.gui.slot;

import java.util.ArrayList;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fluxedCrystals.blocks.FCBlocks;

public class SlotCustom extends Slot {

	ArrayList<ItemStack> validItems = new ArrayList<ItemStack>();

	public SlotCustom(IInventory inventory, int number, int x, int y, ItemStack... validItems) {
		super(inventory, number, x, y);
		for(ItemStack stack : validItems){
			this.validItems.add(stack);
		}
	}
	

	public boolean isItemValid(ItemStack stack) {
		
		for(ItemStack items : validItems){
			if(items.isItemEqual(stack)){
				return true;
			}
		}
		return false;
	}
}