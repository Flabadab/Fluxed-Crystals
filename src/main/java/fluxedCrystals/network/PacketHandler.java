package fluxedCrystals.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fluxedCrystals.ModProps;

public class PacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModProps.modid);

	public static void init() {
		INSTANCE.registerMessage(MessageEnergyUpdate.class, MessageEnergyUpdate.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(MessageManagerBlock.class, MessageManagerBlock.class, 1, Side.SERVER);
		INSTANCE.registerMessage(MessageSeedInfuser.class, MessageSeedInfuser.class, 2, Side.SERVER);
		INSTANCE.registerMessage(MessageSeedInfuser.class, MessageSeedInfuser.class, 3, Side.CLIENT);
		INSTANCE.registerMessage(MessageBiome.class, MessageBiome.class, 4, Side.CLIENT);
		INSTANCE.registerMessage(MessageGemRefiner.class, MessageGemRefiner.class, 5, Side.SERVER);
		INSTANCE.registerMessage(MessageGemRefiner.class, MessageGemRefiner.class, 6, Side.CLIENT);
		INSTANCE.registerMessage(MessageGemCutter.class, MessageGemCutter.class, 7, Side.SERVER);
		INSTANCE.registerMessage(MessageGemCutter.class, MessageGemCutter.class, 8, Side.CLIENT);
		INSTANCE.registerMessage(MessageManagerBlockConvert.class, MessageManagerBlockConvert.class, 9, Side.SERVER);
		INSTANCE.registerMessage(MessageEnergyStorage.class, MessageEnergyStorage.class, 10, Side.CLIENT);
		
		
		
		
		
	}
}