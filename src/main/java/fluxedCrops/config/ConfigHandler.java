package fluxedCrops.config;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;

import org.apache.commons.io.filefilter.FileFilterUtils;

import tterrag.core.common.config.AbstractConfigHandler;
import tterrag.core.common.config.JsonConfigReader;
import tterrag.core.common.config.JsonConfigReader.ModToken;
import tterrag.core.common.util.IOUtils;
import fluxedCrops.FluxedCrops;
import fluxedCrops.ModProps;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.api.recipe.RecipeSeedInfuser;
import fluxedCrops.api.recipe.SeedCropRecipe;
import fluxedCrops.config.json.ISeedType;
import fluxedCrops.config.json.SeedType;
import fluxedCrops.config.json.ThaumcraftSeedType;
import fluxedCrops.items.FCItems;
import fluxedCrops.utils.ModUtils;

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
		ConfigProps.thaumcraftAddon = getProperty("Thaumcraft Addon Support", true).getBoolean(true);
		ConfigProps.enderioAddon = getProperty("EnderIO Addon Support", true).getBoolean(true);
		activateSection(ConfigProps.dropCategory);
		ConfigProps.shardDrop = getProperty("Should crops drop shards or their ingredient", true).getBoolean(true);
		ConfigProps.shard3x3 = getProperty("Should shards craft into the ingredients with 9 of the drops, or with 4 of the drop", true).getBoolean(true);

		RecipeRegistry.reset();

		ModToken token = new ModToken(FluxedCrops.class, ModProps.modid + "/misc/");

		String basePath = FluxedCrops.configDir.getAbsolutePath();
		File[] cropFiles = FluxedCrops.configDir.listFiles((FileFilter) FileFilterUtils.suffixFileFilter(".json"));
		File crops = new File(basePath + "/crops.json");
		if (!crops.exists()) {
			IOUtils.copyFromJar(FluxedCrops.class, ModProps.modid + "/misc/" + "crops.json", crops);
		}
		if (ModUtils.isModLoaded("Thaumcraft") && ConfigProps.thaumcraftAddon) {
			File thaumcraftCrops = new File(basePath + "/thaumcraftCrops.json");
			if (!thaumcraftCrops.exists()) {
				IOUtils.copyFromJar(FluxedCrops.class, ModProps.modid + "/misc/" + "thaumcraftCrops.json", thaumcraftCrops);
			}
		}
		if (ModUtils.isModLoaded("EnderIO") && ConfigProps.enderioAddon) {
			File enderioCrops = new File(basePath + "/enderioCrops.json");
			if (!enderioCrops.exists()) {
				IOUtils.copyFromJar(FluxedCrops.class, ModProps.modid + "/misc/" + "enderioCrops.json", enderioCrops);
			}
		}

		JsonConfigReader<SeedType> cropReader;

		for (int i = 0; i < cropFiles.length; i++) {
			if (cropFiles[i] != null) {
				cropReader = new JsonConfigReader<SeedType>(token, cropFiles[i], SeedType.class);
				registerAll(cropReader.getElements());
			}
		}

		List<SeedCropRecipe> recipes = RecipeRegistry.getSeedCropRecipes();

		for (int i = 0; i < recipes.size(); i++) {
			SeedCropRecipe r = recipes.get(i);
			RecipeRegistry.registerSeedInfuserRecipe(new RecipeSeedInfuser(r.getIngredient(), new ItemStack(FCItems.seed, 1, i)));
		}
	}

	public static void registerAll(Collection<? extends ISeedType> types) {
		for (ISeedType type : types) {
			type.register();
		}
	}
}
