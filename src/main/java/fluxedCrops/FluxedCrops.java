package fluxedCrops;

import java.io.File;
import java.util.List;

import net.minecraft.item.ItemStack;

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
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.recipe.RecipeSeedInfuser;
import fluxedCrops.api.recipe.SeedCropRecipe;
import fluxedCrops.blocks.FCBlocks;
import fluxedCrops.client.gui.GUIHandler;
import fluxedCrops.config.ConfigHandler;
import fluxedCrops.handlers.ThaumcraftHandler;
import fluxedCrops.items.FCItems;
import fluxedCrops.network.PacketHandler;
import fluxedCrops.proxy.CommonProxy;

@Mod(modid = ModProps.modid, name = ModProps.name, version = ModProps.version, dependencies = "required-after:ThermalFoundation;required-after:CoFHCore;after:Thaumcraft")
public class FluxedCrops {

	@Instance("fluxedcrops")
	public static FluxedCrops instance;
	@SidedProxy(clientSide = "fluxedCrops.proxy.ClientProxy", serverSide = "fluxedCrops.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final Lang lang = new Lang(ModProps.modid);

	public static final Logger logger = LogManager.getLogger(ModProps.name);
	
	public static final CreativeTabFluxedCrops tab = new CreativeTabFluxedCrops();
	public static int cropRenderID;
	
	public static File configDir = null;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger.info("Starting Pre Init.");
		configDir = new File(event.getSuggestedConfigurationFile().getParentFile().getAbsolutePath() + "/fluxedCrops");
		ConfigHandler.INSTANCE.initialize(new File(configDir.getAbsolutePath() + "/fluxedCrops.cfg"));

		FCItems.init();
		FCBlocks.init();
		ThaumcraftHandler.init();

		PacketHandler.init();
		new GUIHandler();
		proxy.initRenderers();
	}

	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		logger.info("Starting Init.");
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		logger.info("Starting Post Init.");
		ThaumcraftHandler.postInit();
	}

}
