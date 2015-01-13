package fluxedCrystals.config;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import java.util.List;

import minetweaker.MineTweakerAPI;
import minetweaker.MineTweakerImplementationAPI;
import minetweaker.api.minecraft.MineTweakerMC;
import minetweaker.mc1710.util.MineTweakerHacks;
import net.minecraft.item.ItemStack;

import org.apache.commons.io.filefilter.FileFilterUtils;

import tterrag.core.common.config.AbstractConfigHandler;
import tterrag.core.common.config.JsonConfigReader;
import tterrag.core.common.config.JsonConfigReader.ModToken;
import tterrag.core.common.util.TTFileUtils;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.ModProps;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.RecipeGemCutter;
import fluxedCrystals.api.recipe.RecipeGemRefiner;
import fluxedCrystals.api.recipe.RecipeSeedInfuser;
import fluxedCrystals.api.recipe.SeedCrystalRecipe;
import fluxedCrystals.config.json.ISeedType;
import fluxedCrystals.config.json.SeedType;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.utils.ModUtils;

public class ConfigHandler extends AbstractConfigHandler {

	public static final ConfigHandler INSTANCE = new ConfigHandler();

	private ConfigHandler() {
		super(ModProps.modid);
	}

	@Override
	protected void init() {
		addSection(ConfigProps.addonCategory, "Addons");
		addSection(ConfigProps.dropCategory, "Drops");

	}

	@Override
	protected void reloadNonIngameConfigs() {
	}

	@Override
	protected void reloadIngameConfigs() {
		activateSection(ConfigProps.addonCategory);
		ConfigProps.enderioAddon = getProperty("EnderIO Addon Support", true).getBoolean(true);

		activateSection(ConfigProps.dropCategory);
		ConfigProps.normalShardRecipes = getProperty("Should materials be crafted in a normal crafting table?", false).getBoolean(false);
		ConfigProps.shard3x3 = getProperty("Should shards craft into the ingredients with 9 of the drops, or with 4 of the drop?", true).getBoolean(true);
		RecipeRegistry.reset();

		ModToken token = new ModToken(FluxedCrystals.class, ModProps.modid + "/misc/");

		String basePath = FluxedCrystals.configDir.getAbsolutePath();
		File[] cropFiles = FluxedCrystals.configDir.listFiles((FileFilter) FileFilterUtils.suffixFileFilter(".json"));
		File crops = new File(basePath + "/Crystal.json");
		File thermalCrops = new File(basePath + "/thermalCrystal.json");

		if (!crops.exists()) {
			TTFileUtils.copyFromJar(FluxedCrystals.class, ModProps.modid + "/misc/" + "Crystal.json", crops);
		}
		if (!thermalCrops.exists() && ModUtils.isModLoaded("ThermalFoundation")) {
			TTFileUtils.copyFromJar(FluxedCrystals.class, ModProps.modid + "/misc/thermalCrystal.json", thermalCrops);
		}

		if (ModUtils.isModLoaded("EnderIO") && ConfigProps.enderioAddon) {
			File enderioCrops = new File(basePath + "/enderioCrystal.json");
			if (!enderioCrops.exists()) {
				TTFileUtils.copyFromJar(FluxedCrystals.class, ModProps.modid + "/misc/" + "enderioCrystal.json", enderioCrops);
			}
		}
		JsonConfigReader<SeedType> cropReader;
		List<SeedCrystalRecipe> recipes = RecipeRegistry.getSeedCropRecipes();
		for (int i = 0; i < cropFiles.length; i++) {
			if (cropFiles[i] != null) {
				cropReader = new JsonConfigReader<SeedType>(token, cropFiles[i], SeedType.class);
				registerAll(cropReader.getElements());

			}
		}

		int i = 0;
		for (SeedCrystalRecipe r : RecipeRegistry.getSeedCropRecipes()) {
			RecipeRegistry.registerGemRefinerRecipe(new RecipeGemRefiner(new ItemStack(FCItems.shard, 1, i), r.getIngredient(), r.getRefinerAmount(), RecipeRegistry.getDropAmount(r.getDropMin(), r.getDropMax())));
			RecipeRegistry.registerGemCutterRecipe(new RecipeGemCutter(new ItemStack(FCItems.roughShard, 1, i), new ItemStack(FCItems.shard, 1, i), 1, 1));
			RecipeRegistry.registerSeedInfuserRecipe(new RecipeSeedInfuser(new ItemStack(FCItems.universalSeed), r.getIngredient(), new ItemStack(FCItems.seed, 1, i), RecipeRegistry.getIngredientAmount(i)));
			i++;
		}
	}

	public static void registerAll(Collection<? extends ISeedType> types) {
		for (ISeedType type : types) {
			type.register();
		}
	}
}
