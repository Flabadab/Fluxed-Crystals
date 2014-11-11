package fluxedCrops.client.gui.seedInfuser;

import fluxedCrops.api.ISeed;
import fluxedCrops.blocks.BlockPowerBlock;
import fluxedCrops.blocks.FCBlocks;
import fluxedCrops.client.gui.slot.SlotPowerBlock;
import fluxedCrops.client.gui.slot.SlotSeed;
import fluxedCrops.tileEntity.TileEntityManagerBlock;
import fluxedCrops.tileEntity.TileEntitySeedInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerSeedInfuser extends Container {

	public ContainerSeedInfuser(InventoryPlayer invPlayer, TileEntitySeedInfuser manager) {
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 142));
		}

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 84 + y * 18));
			}
		}

		addSlotToContainer(new SlotSeed(manager, 0, 9, 9));
		addSlotToContainer(new Slot(manager, 1, 151, 9));
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

	 /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber)
    {

        return null;
    }
}
