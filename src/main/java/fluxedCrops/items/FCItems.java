package fluxedCrops.items;

import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.ModProps;
import fluxedCrops.config.ConfigProps;
import fluxedCrops.items.seeds.*;
import fluxedCrops.items.seeds.ItemIronSeed;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class FCItems {

    public static Item ironShard = new ItemShard();
    public static Item ironSeed = new ItemIronSeed();

    public static void init() {
        registerItems();
        registerNBT();
        registerRecipes();
    }

    private static void registerRecipes() {

    }

    private static void registerItems() {
        registerShard(ironShard, "Iron Shard", "ironShard", ConfigProps.ironColor);
        registerItem(ironSeed, "Iron Seed", "seedIron", null);
    }




    private static void registerNBT() {

    }
    private static void registerItem(Item item, String name, String key) {
        item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + key).setCreativeTab(CreativeTabs.tabBlock);
        GameRegistry.registerItem(item, name);
    }

    private static void registerItem(Item item, String name, String key, CreativeTabs tabs) {
        item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + key).setCreativeTab(tabs);
        GameRegistry.registerItem(item, name);
    }

    private static void registerShard(Item item, String name, String key, int color) {
        ((ItemShard)item).setColor(color);
        item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":shard").setCreativeTab(CreativeTabs.tabBlock);
        GameRegistry.registerItem(item, name);
    }
}
