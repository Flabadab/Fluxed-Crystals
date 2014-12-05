package fluxedCrystals.client.gui.thaumicManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fluxedCrystals.api.ISeed;
import fluxedCrystals.client.gui.slot.SlotPowerBlock;
import fluxedCrystals.client.gui.slot.SlotUpgrade;
import fluxedCrystals.tileEntity.TileEntityBloodManager;
import fluxedCrystals.tileEntity.TileEntityManagerBlock;
import fluxedCrystals.tileEntity.TileEntityThaumicManager;

public class ContainerThaumicManager extends Container {

	public ContainerThaumicManager(InventoryPlayer invPlayer, TileEntityThaumicManager manager) {
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
