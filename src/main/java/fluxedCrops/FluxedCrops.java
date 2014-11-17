package fluxedCrops;

import java.io.File;

import tterrag.core.common.Lang;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fluxedCrops.blocks.FCBlocks;
import fluxedCrops.client.gui.GUIHandler;
import fluxedCrops.config.ConfigHandler;
import fluxedCrops.handlers.ThaumcraftHandler;
import fluxedCrops.items.FCItems;
import fluxedCrops.network.PacketHandler;
import fluxedCrops.proxy.CommonProxy;
import fluxedCrops.utils.RecipeHandler;

@Mod(modid = ModProps.modid, name = ModProps.name, version = ModProps.version, dependencies = "required-after:ThermalFoundation;required-after:CoFHCore;after:Thaumcraft")
public class FluxedCrops {

	@Instance("fluxedcrops")
	public static FluxedCrops instance;
	@SidedProxy(clientSide = "fluxedCrops.proxy.ClientProxy", serverSide = "fluxedCrops.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static final Lang lang = new Lang("fluxedCrops");

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(new File(event.getSuggestedConfigurationFile().getParentFile() + "/fluxedCrops/fluxedCrops.cfg"));
		FCItems.init();
		FCBlocks.init();
		ThaumcraftHandler.init();
		PacketHandler.init();
		RecipeHandler.init();
		new GUIHandler();
	}

	@EventHandler
	public static void Init(FMLInitializationEvent event) {
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {

	}

}
