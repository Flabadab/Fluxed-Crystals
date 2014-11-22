package fluxedCrops.config;

import java.util.Collection;
import java.util.List;

import net.minecraft.item.ItemStack;
import tterrag.core.common.config.AbstractConfigHandler;
import tterrag.core.common.config.JsonConfigReader;
import tterrag.core.common.config.JsonConfigReader.ModToken;
import fluxedCrops.FluxedCrops;
import fluxedCrops.ModProps;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.recipe.RecipeSeedInfuser;
import fluxedCrops.api.recipe.SeedCropRecipe;
import fluxedCrops.config.json.ISeedType;
import fluxedCrops.config.json.SeedType;
import fluxedCrops.config.json.ThaumcraftSeedType;
import fluxedCrops.items.FCItems;

public class ConfigHandler extends AbstractConfigHandler {

	public static final ConfigHandler INSTANCE = new ConfigHandler();
	
	private ConfigHandler() {
		super(ModProps.modid);
	}

	@Override
	protected void init() {
		; // add future sections here
	}

	@Override
	protected void reloadNonIngameConfigs() {
		; // none yet
	}

	@Override
	protected void reloadIngameConfigs() {
		
		RecipeRegistry.reset();
		
		ModToken token = new ModToken(FluxedCrops.class, ModProps.modid + "/misc/");
		
		String basePath = FluxedCrops.configDir.getAbsolutePath();

		JsonConfigReader<SeedType> seedReader = new JsonConfigReader<SeedType>(token, basePath + "/crops.json", SeedType.class);
		JsonConfigReader<ThaumcraftSeedType> tcReader = new JsonConfigReader<ThaumcraftSeedType>(token, basePath + "/thaumcraftCrops.json", ThaumcraftSeedType.class);

		registerAll(seedReader.getElements());
		registerAll(tcReader.getElements());
		
		List<SeedCropRecipe> recipes = RecipeRegistry.getSeedCropRecipes();

		for (int i = 0; i < recipes.size(); i++) {
			SeedCropRecipe r = recipes.get(i);
			RecipeRegistry.registerSeedInfuserRecipe(new RecipeSeedInfuser(r.getIngredient(), new ItemStack(FCItems.seed, 1, i)));
		}
	}
	
//	public static void init(File file) {
//
//
//		// ConfigProps.ironColor = config.get(seedColor, "Iron Color",
//		// 0xd6b781).getInt(0xd6b781);
//		// ConfigProps.goldColor = config.get(seedColor, "Gold Color",
//		// 0xFFD700).getInt(0xFFD700);
//		// ConfigProps.coalColor = config.get(seedColor, "Coal Color",
//		// 0x141414).getInt(0x141414);
//		// ConfigProps.charcoalColor = config.get(seedColor, "Charcoal Color",
//		// 0x252017).getInt(0x252017);
//		// ConfigProps.colorEmerald = config.get(seedColor, "Emerald Color",
//		// 0x2fa951).getInt(0x2fa951);
//		// ConfigProps.colorDiamond = config.get(seedColor, "Diamond Color",
//		// 0x58ad9e).getInt(0x58ad9e);
//		// ConfigProps.colorRedstone = config.get(seedColor, "Redstone Color",
//		// 0xe83838).getInt(0xe83838);
//		//
//		// ConfigProps.colorCopper = config.get(seedColor, "Copper Color",
//		// 0xad6e17).getInt(0xad6e17);
//		// ConfigProps.colorElectrum = config.get(seedColor, "Electrum Color",
//		// 0xb4a54c).getInt(0xb4a54c);
//		// ConfigProps.colorEnderium = config.get(seedColor, "Enderium Color",
//		// 0x0c5253).getInt(0x0c5253);
//		// ConfigProps.colorFerrous = config.get(seedColor, "Ferrous Color",
//		// 0xc1ba8e).getInt(0xc1ba8e);
//		// ConfigProps.colorInvar = config.get(seedColor, "Invar Color",
//		// 0x959d99).getInt(0x959d99);
//		// ConfigProps.colorLead = config.get(seedColor, "Lead Color",
//		// 0x525a74).getInt(0x525a74);
//		// ConfigProps.colorLumium = config.get(seedColor, "Lumium Color",
//		// 0xdcc47d).getInt(0xdcc47d);
//		// ConfigProps.colorMana = config.get(seedColor, "Mana Color",
//		// 0x53788c).getInt(0x53788c);
//		// ConfigProps.colorShiny = config.get(seedColor, "Shiny Ingot Color",
//		// 0x77b5e1).getInt(0x77b5e1);
//		// ConfigProps.colorSignalum = config.get(seedColor, "Signalum Color",
//		// 0xae490e).getInt(0xae490e);
//		// ConfigProps.colorSilver = config.get(seedColor, "Silver Color",
//		// 0x97a9af).getInt(0x97a9af);
//		// ConfigProps.colorTin = config.get(seedColor, "Tin Color",
//		// 0x738998).getInt(0x738998);
//		// ConfigProps.colorTinkers = config.get(seedColor,
//		// "Tinkers alloy Color", 0xcf9e65).getInt(0xcf9e65);
//
//	}
//	
	public static void registerAll(Collection<? extends ISeedType> types) {
		for (ISeedType type : types) {
			type.register();
		}
	}
}
