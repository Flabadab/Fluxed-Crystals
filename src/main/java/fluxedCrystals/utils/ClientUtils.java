package fluxedCrystals.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import fluxedCrystals.network.MessageEnergyUpdate;
import fluxedCrystals.tileEntity.TileEnergyBase;

public class ClientUtils {

	public static void updateEnergy(MessageEnergyUpdate message) {
		TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileEnergyBase) {
			((TileEnergyBase) te).setEnergyStored(message.stored);
		}
	}

}
