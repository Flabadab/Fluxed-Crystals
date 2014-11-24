package fluxedCrops.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fluxedCrops.FluxedCrops;
import fluxedCrops.client.render.CropRenderer;
import fluxedCrops.client.render.SeedInfuserRenderer;

public class ClientProxy extends CommonProxy {

	@Override
	public void initRenderers() {
		FluxedCrops.cropRenderID = RenderingRegistry.getNextAvailableRenderId();
		FluxedCrops.seedInfuserRenderID = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new CropRenderer());
		RenderingRegistry.registerBlockHandler(new SeedInfuserRenderer());
	}
	
	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
}
