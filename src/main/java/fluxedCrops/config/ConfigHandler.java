package fluxedCrops.config;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import java.util.List;

import net.minecraft.item.ItemStack;

import org.apache.commons.io.filefilter.FileFilterUtils;

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
		File[] cropFiles = FluxedCrops.configDir.listFiles((FileFilter) FileFilterUtils.suffixFileFilter(".json"));

		for (int i = 0; i < cropFiles.length; i++) {
			if (cropFiles[i] != null) {
				JsonConfigReader<SeedType> cropReader = new JsonConfigReader<SeedType>(token, cropFiles[i], SeedType.class);
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
