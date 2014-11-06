package fluxedCrops.utils;

import fluxedCrops.network.MessageEnergyUpdate;
import fluxedCrops.tileEntity.TileEnergyBase;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

public class ClientUtils {

	public static void updateEnergy(MessageEnergyUpdate message) {
		TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileEnergyBase) {
			((TileEnergyBase) te).setEnergyStored(message.stored);
		}
	}

}
