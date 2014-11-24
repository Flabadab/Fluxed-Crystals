package fluxedCrops.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fluxedCrops.FluxedCrops;
import fluxedCrops.client.render.CropRenderer;
import fluxedCrops.client.render.SeedInfuserRenderer;

public class ClientProxy extends CommonProxy {

	public void initRenderers() {
		FluxedCrops.cropRenderID = RenderingRegistry.getNextAvailableRenderId();
		FluxedCrops.seedInfuserRenderID = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new CropRenderer());
		RenderingRegistry.registerBlockHandler(new SeedInfuserRenderer());
	}

}
