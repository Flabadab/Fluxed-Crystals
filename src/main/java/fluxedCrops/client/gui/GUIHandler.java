package fluxedCrops.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import fluxedCrops.FluxedCrops;
import fluxedCrops.tileEntity.TileEntityManagerBlock;

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
		}

		return null;
	}

}
