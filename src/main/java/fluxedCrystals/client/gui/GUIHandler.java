package fluxedCrystals.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.client.gui.bloodManager.ContainerBloodManager;
import fluxedCrystals.client.gui.bloodManager.GUIBloodManager;
import fluxedCrystals.client.gui.manager.ContainerManagerBlock;
import fluxedCrystals.client.gui.manager.GUIManagerBlock;
import fluxedCrystals.client.gui.seedInfuser.ContainerSeedInfuser;
import fluxedCrystals.client.gui.seedInfuser.GUISeedInfuser;
import fluxedCrystals.client.gui.thaumicManager.ContainerThaumicManager;
import fluxedCrystals.client.gui.thaumicManager.GUIThaumicManager;
import fluxedCrystals.tileEntity.TileEntityBloodManager;
import fluxedCrystals.tileEntity.TileEntityManagerBlock;
import fluxedCrystals.tileEntity.TileEntitySeedInfuser;
import fluxedCrystals.tileEntity.TileEntityThaumicManager;

public class GUIHandler implements IGuiHandler {

	public GUIHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(FluxedCrystals.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		switch (ID) {

		case 0:

			if (te != null && te instanceof TileEntityManagerBlock) {
				return new ContainerManagerBlock(player.inventory, (TileEntityManagerBlock) te);
			}
			break;

		case 1:
			if (te != null && te instanceof TileEntitySeedInfuser) {
				return new ContainerSeedInfuser(player.inventory, (TileEntitySeedInfuser) te);
			}
			break;

		case 2:
			if (te != null && te instanceof TileEntityBloodManager) {
				return new ContainerBloodManager(player.inventory, (TileEntityBloodManager) te);
			}
			break;
		case 3:
			if (te != null && te instanceof TileEntityThaumicManager) {
				return new ContainerThaumicManager(player.inventory, (TileEntityThaumicManager) te);
			}
			break;

		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		switch (ID) {
		case 0:
			if (te != null && te instanceof TileEntityManagerBlock) {
				return new GUIManagerBlock(player.inventory, (TileEntityManagerBlock) te);
			}
			break;
		case 1:
			if (te != null && te instanceof TileEntitySeedInfuser) {
				return new GUISeedInfuser(player.inventory, (TileEntitySeedInfuser) te);
			}
			break;
		case 2:
			if (te != null && te instanceof TileEntityBloodManager) {
				return new GUIBloodManager(player.inventory, (TileEntityBloodManager) te);
			}
			break;
		case 3:
			if (te != null && te instanceof TileEntityThaumicManager) {
				return new GUIThaumicManager(player.inventory, (TileEntityThaumicManager) te);
			}
			break;
		}

		return null;
	}

}
