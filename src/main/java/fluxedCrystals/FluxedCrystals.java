package fluxedCrystals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tterrag.core.TTCore;
import tterrag.core.common.Lang;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import fluxedCrystals.blocks.FCBlocks;
import fluxedCrystals.compat.minetweaker.FluxedCrystalsMT;
import fluxedCrystals.compat.minetweaker.helper.TweakerPlugin;
import fluxedCrystals.config.ConfigHandler;
import fluxedCrystals.handlers.RecipeHandler;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.nei.FluxedCrystalsNEIConfig;
import fluxedCrystals.network.PacketHandler;
import fluxedCrystals.proxy.CommonProxy;

@Mod(modid = ModProps.modid, name = ModProps.name, version = ModProps.version, dependencies = "after:ThermalFoundation;required-after:ttCore;after:EnderIO;after:AWWayofTime;after:botania;after:NotEnoughItems;after:AgriCraft;after:MineTweaker3")
public class FluxedCrystals {

	public static File configDir = null;
	public static int crystalRenderID;

	@Instance("fluxedcrystals")
	public static FluxedCrystals instance;

	public static final Lang lang = new Lang(ModProps.modid);

	public static final Logger logger = LogManager.getLogger(ModProps.name);
	@SidedProxy(clientSide = "fluxedCrystals.proxy.ClientProxy", serverSide = "fluxedCrystals.proxy.CommonProxy")
	public static CommonProxy proxy;
	public static int seedInfuserRenderID;
	public static int glassRenderID;
	public static int chunkRenderID;
	public static boolean thaumcraftThere;
	public static List<ModContainer> activeMods = new ArrayList<ModContainer>();
	public static final CreativeTabFluxedCrystals tab = new CreativeTabFluxedCrystals();

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger.info("Starting Pre Init.");
		configDir = new File(event.getSuggestedConfigurationFile().getParentFile().getAbsolutePath() + "/fluxedCrystals");
		ConfigHandler.INSTANCE.initialize(new File(configDir.getAbsolutePath() + "/fluxedCrystals.cfg"));
		FCItems.init();
		FCBlocks.init();
		PacketHandler.init();
		proxy.initGuis();
		proxy.initRenderers();
		if (Loader.isModLoaded("Thaumcraft")) {
			thaumcraftThere = true;
		} else {
			thaumcraftThere = false;
		}
		if (Loader.isModLoaded("NotEnoughItems") && event.getSide() == Side.CLIENT) {
			new FluxedCrystalsNEIConfig().loadConfig();
		}
		FMLInterModComms.sendMessage("Waila", "register", "fluxedCrystals.compat.waila.WailaCompat.load");
		ConfigHandler.INSTANCE.reloadIngameConfigs();
	}

	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		logger.info("Starting Init.");
		proxy.renderTrans();
		ConfigHandler.INSTANCE.reloadIngameConfigs();
		if (Loader.isModLoaded("MineTweaker3"))
			TweakerPlugin.register(ModProps.modid, FluxedCrystalsMT.class);
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		logger.info("Starting Post Init.");
		RecipeHandler.init();
		activeMods = Loader.instance().getActiveModList();
		ConfigHandler.INSTANCE.reloadIngameConfigs();
	}
	
	@EventHandler
	public static void serverStart(FMLServerStartingEvent event){
		ConfigHandler.INSTANCE.reloadIngameConfigs();
	}

}
