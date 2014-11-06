package fluxedCrops.client.gui;

import fluxedCrops.blocks.BlockPowerBlock;
import fluxedCrops.blocks.FCBlocks;
import fluxedCrops.client.gui.slot.SlotPowerBlock;
import fluxedCrops.tileEntity.TileEntityManagerBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

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
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return null;
	}
}
