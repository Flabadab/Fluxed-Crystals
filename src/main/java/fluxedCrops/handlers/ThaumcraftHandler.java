package fluxedCrops.handlers;

import fluxedCrops.ModProps;
import fluxedCrops.items.FCItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ThaumcraftHandler {

	public static void init() {
		registerOthers();

	}

	public static void postInit() {
		registerPages();
	}

	private static ResourceLocation background = new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png");
	private static String category = "Fluxed-Crops";

	private static void registerPages() {
		registerResearchPages();

		ResearchItem research = new ResearchItem(category, category, new AspectList().add(Aspect.AIR, 1), 50, 50, 0, new ItemStack(FCItems.dustDiamond));
		research.setPages(new ResearchPage("hello")).setAutoUnlock().registerResearchItem();
		ResearchCategories.addResearch(research);
	}

	private static void registerResearchPages() {
		ResearchCategories.registerCategory(category, new ResourceLocation(ModProps.modid, "textures/blocks/gold_stage_7.png"), background);
	}

	private static void registerOthers() {

	}

}
