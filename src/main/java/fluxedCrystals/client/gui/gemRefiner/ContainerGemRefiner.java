package fluxedCrystals.client.gui.gemRefiner;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fluxedCrystals.client.gui.slot.SlotIBindable;
import fluxedCrystals.client.gui.slot.SlotUpgrade;
import fluxedCrystals.tileEntity.TileEntityGemRefiner;

public class ContainerGemRefiner extends Container {

	public ContainerGemRefiner(InventoryPlayer invPlayer, TileEntityGemRefiner manager) {

		addSlotToContainer(new Slot(manager, 0, 9, 9));
		addSlotToContainer(new Slot(manager, 1, 151, 9));

		addSlotToContainer(new SlotUpgrade(manager, 2, 116, 62));
		addSlotToContainer(new SlotUpgrade(manager, 3, 134, 62));
		addSlotToContainer(new SlotUpgrade(manager, 4, 152, 62));

		addSlotToContainer(new SlotIBindable(manager, 5, 76, 62));

		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 142));
		}

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 84 + y * 18));
			}
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {

		return null;
	}
}
