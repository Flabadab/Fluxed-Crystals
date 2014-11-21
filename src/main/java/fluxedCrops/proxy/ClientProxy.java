package fluxedCrops.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fluxedCrops.FluxedCrops;
import fluxedCrops.client.render.CropRenderer;

public class ClientProxy extends CommonProxy {

	public void initRenderers() {
		FluxedCrops.cropRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new CropRenderer());
	}

}
