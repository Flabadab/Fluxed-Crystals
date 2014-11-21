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

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger.info("Starting Pre Init.");
		ConfigHandler.init(new File(event.getSuggestedConfigurationFile().getParentFile() + "/fluxedCrops/fluxedCrops.cfg"));
		List<SeedCropRecipe> recipes = RecipeRegistry.getSeedCropRecipes();

		FCItems.init();
		FCBlocks.init();
		ThaumcraftHandler.init();
		for (int i = 0; i < recipes.size(); i++) {
			SeedCropRecipe r = recipes.get(i);
			RecipeRegistry.registerSeedInfuserRecipe(new RecipeSeedInfuser(r.getIngredient(), new ItemStack(FCItems.seed, 1, i)));
		}
		PacketHandler.init();
		new GUIHandler();
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
