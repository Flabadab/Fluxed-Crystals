package fluxedCrops.client.gui.manager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fluxedCrops.api.ISeed;
import fluxedCrops.client.gui.slot.SlotPowerBlock;
import fluxedCrops.client.gui.slot.SlotUpgrade;
import fluxedCrops.tileEntity.TileEntityManagerBlock;

public class ContainerManagerBlock extends Container {

	public ContainerManagerBlock(InventoryPlayer invPlayer, TileEntityManagerBlock manager) {
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 142));
		}

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 84 + y * 18));
			}
		}

		addSlotToContainer(new SlotPowerBlock(manager, 0, 8, 62));
		addSlotToContainer(new SlotPowerBlock(manager, 1, 26, 62));
	
		addSlotToContainer(new SlotUpgrade(manager, 2, 116, 62));
		addSlotToContainer(new SlotUpgrade(manager, 3, 134, 62));
		addSlotToContainer(new SlotUpgrade(manager, 4, 152, 62));
		
		
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
