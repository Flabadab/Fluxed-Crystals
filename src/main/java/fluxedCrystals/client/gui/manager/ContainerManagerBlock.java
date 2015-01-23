package fluxedCrystals.client.gui.manager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fluxedCrystals.api.ISeed;
import fluxedCrystals.client.gui.slot.SlotCustom;
import fluxedCrystals.client.gui.slot.SlotIBindable;
import fluxedCrystals.client.gui.slot.SlotIMana;
import fluxedCrystals.client.gui.slot.SlotPowerBlock;
import fluxedCrystals.client.gui.slot.SlotUpgrade;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.tileEntity.TileEntityManagerBlock;

public class ContainerManagerBlock extends Container {

	public ContainerManagerBlock(InventoryPlayer invPlayer, TileEntityManagerBlock manager) {
		addSlotToContainer(new SlotCustom(manager, 0, 13, 62,1, new ItemStack(FCItems.upgradeRangeBasic), new ItemStack(FCItems.upgradeRangeAdvanced), new ItemStack(FCItems.upgradeRangeGreater)));
		addSlotToContainer(new SlotUpgrade(manager, 1, 147, 8));
		addSlotToContainer(new SlotUpgrade(manager, 2, 147, 26));
		addSlotToContainer(new SlotUpgrade(manager, 3, 147, 44));
		addSlotToContainer(new SlotIBindable(manager, 4, 147, 62));
		
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

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {
		return null;
	}
}
