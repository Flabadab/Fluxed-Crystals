package fluxedCrystals.proxy;

import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.client.render.CrystalRenderer;
import fluxedCrystals.client.render.SeedInfuserRenderer;

public class ClientProxy extends CommonProxy {

	@Override
	public void initRenderers() {
		FluxedCrystals.crystalRenderID = RenderingRegistry.getNextAvailableRenderId();
		FluxedCrystals.seedInfuserRenderID = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new CrystalRenderer());
		RenderingRegistry.registerBlockHandler(new SeedInfuserRenderer());
	}
	
	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}
}
