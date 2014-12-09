package fluxedCrystals.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fluxedCrystals.ModProps;

public class PacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModProps.modid);

	public static void init() {
		INSTANCE.registerMessage(MessageEnergyUpdate.class, MessageEnergyUpdate.class, 0, Side.SERVER);
		INSTANCE.registerMessage(MessageManagerBlock.class, MessageManagerBlock.class, 1, Side.SERVER);
		INSTANCE.registerMessage(MessageSeedInfuser.class, MessageSeedInfuser.class, 2, Side.SERVER);
		INSTANCE.registerMessage(MessageSeedInfuser.class, MessageSeedInfuser.class, 3, Side.CLIENT);
		INSTANCE.registerMessage(MessageBiome.class, MessageBiome.class, 4, Side.CLIENT);
		INSTANCE.registerMessage(MessageBloodManager.class, MessageBloodManager.class, 5, Side.SERVER);
		INSTANCE.registerMessage(MessageThaumicManager.class, MessageThaumicManager.class, 6, Side.SERVER);
		INSTANCE.registerMessage(MessageManaManager.class, MessageManaManager.class, 7, Side.SERVER);
		INSTANCE.registerMessage(MessageIndustrialManager.class, MessageIndustrialManager.class, 8, Side.SERVER);
		
		
	}
}