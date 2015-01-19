package fluxedCrystals.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.client.gui.GUIHandler;
import fluxedCrystals.client.render.ChunkRenderer;
import fluxedCrystals.client.render.CrystalRenderer;
import fluxedCrystals.client.render.GlassRenderer;
import fluxedCrystals.client.render.SeedInfuserRenderer;
import fluxedCrystals.utils.EventHandler;

public class ClientProxy extends CommonProxy {

	public void initGuis() {
		new GUIHandler();
	}

	@Override
	public void initRenderers() {
		FluxedCrystals.crystalRenderID = RenderingRegistry.getNextAvailableRenderId();
		FluxedCrystals.seedInfuserRenderID = RenderingRegistry.getNextAvailableRenderId();
		FluxedCrystals.glassRenderID = RenderingRegistry.getNextAvailableRenderId();
		FluxedCrystals.chunkRenderID = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new CrystalRenderer());
		RenderingRegistry.registerBlockHandler(new SeedInfuserRenderer());
		RenderingRegistry.registerBlockHandler(new GlassRenderer());
		RenderingRegistry.registerBlockHandler(new ChunkRenderer());
	}

	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	public void renderTrans() {
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
}
