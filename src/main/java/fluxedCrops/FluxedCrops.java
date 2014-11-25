package fluxedCrops;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import fluxedCrops.handlers.RecipeHandler;
import fluxedCrops.items.FCItems;
import fluxedCrops.network.PacketHandler;
import fluxedCrops.proxy.CommonProxy;

@Mod(modid = ModProps.modid, name = ModProps.name, version = ModProps.version, dependencies = "required-after:ThermalFoundation;after:Thaumcraft;required-after:ttCore;after:EnderIO")
public class FluxedCrops {

	public static File configDir = null;
	public static int cropRenderID;

	@Instance("fluxedcrops")
	public static FluxedCrops instance;

	public static final Lang lang = new Lang(ModProps.modid);

	public static final Logger logger = LogManager.getLogger(ModProps.name);
	@SidedProxy(clientSide = "fluxedCrops.proxy.ClientProxy", serverSide = "fluxedCrops.proxy.CommonProxy")
	public static CommonProxy proxy;
	public static int seedInfuserRenderID;

	public static final CreativeTabFluxedCrops tab = new CreativeTabFluxedCrops();

	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		logger.info("Starting Init.");
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		logger.info("Starting Post Init.");
	}

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger.info("Starting Pre Init.");
		configDir = new File(event.getSuggestedConfigurationFile().getParentFile().getAbsolutePath() + "/fluxedCrops");
		ConfigHandler.INSTANCE.initialize(new File(configDir.getAbsolutePath() + "/fluxedCrops.cfg"));
		FCItems.init();
		FCBlocks.init();
		PacketHandler.init();
		new GUIHandler();
		proxy.initRenderers();
		RecipeHandler.init();
	}

}
