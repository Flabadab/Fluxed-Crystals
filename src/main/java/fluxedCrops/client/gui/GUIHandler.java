package fluxedCrops.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import fluxedCrops.FluxedCrops;
import fluxedCrops.client.gui.manager.ContainerManagerBlock;
import fluxedCrops.client.gui.manager.GUIManagerBlock;
import fluxedCrops.client.gui.seedInfuser.ContainerSeedInfuser;
import fluxedCrops.client.gui.seedInfuser.GUISeedInfuser;
import fluxedCrops.tileEntity.TileEntityManagerBlock;
import fluxedCrops.tileEntity.TileEntitySeedInfuser;

public class GUIHandler implements IGuiHandler {

	public GUIHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(FluxedCrops.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {

		case 0:
			TileEntity te = world.getTileEntity(x, y, z);
			if (te != null && te instanceof TileEntityManagerBlock) {
				return new ContainerManagerBlock(player.inventory, (TileEntityManagerBlock) te);
			}
			break;

		case 1:
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null && tile instanceof TileEntitySeedInfuser) {
				return new ContainerSeedInfuser(player.inventory, (TileEntitySeedInfuser) tile);
			}
			break;
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 0:
			TileEntity te = world.getTileEntity(x, y, z);
			if (te != null && te instanceof TileEntityManagerBlock) {
				return new GUIManagerBlock(player.inventory, (TileEntityManagerBlock) te);
			}
			break;
		case 1:
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null && tile instanceof TileEntitySeedInfuser) {
				return new GUISeedInfuser(player.inventory, (TileEntitySeedInfuser) tile);
			}
			break;

		}

		return null;
	}

}
