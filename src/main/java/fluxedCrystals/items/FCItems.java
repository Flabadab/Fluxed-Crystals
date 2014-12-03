package fluxedCrystals.items;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.ModProps;
import fluxedCrystals.items.seeds.ItemSeed;
import fluxedCrystals.items.seeds.ItemUniversalSeed;
import fluxedCrystals.items.upgrades.Upgrade;

public class FCItems {

    public static Item universalSeed = new ItemUniversalSeed();
    public static Item seed = new ItemSeed();
    public static Item shard = new ItemShard();

    public static Item scytheWood = new ItemScythe();
    public static Item scytheStone = new ItemScythe();
    public static Item scytheIron = new ItemScythe();
    public static Item scytheGold = new ItemScythe();
    public static Item scytheDiamond = new ItemScythe();

    public static Item upgradeEffeciency = new Upgrade();
    public static Item upgradeNight = new Upgrade();
    public static Item upgradeSpeed = new Upgrade();
    public static Item upgradeAutomation = new Upgrade();

    public static void init() {
        registerItems();
        registerNBT();
        registerRecipes();

    }

    private static void registerRecipes() {

    }

    private static void registerItems() {
        seed.setCreativeTab(FluxedCrystals.tab);
        shard.setCreativeTab(FluxedCrystals.tab);
        universalSeed.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":seed").setUnlocalizedName("universalSeed");
        upgradeEffeciency.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeEffeciency").setUnlocalizedName("upgradeEfficiency");
        upgradeNight.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeNight").setUnlocalizedName("upgradeNight");
        upgradeSpeed.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeSpeed").setUnlocalizedName("upgradeSpeed");
        upgradeAutomation.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeAutomation").setUnlocalizedName("upgradeAutomation");

        registerScythe(scytheWood, "Wooden Scythe", "scytheWood");
        registerScythe(scytheStone, "Stone Scythe", "scytheStone");
        registerScythe(scytheIron, "Iron Scythe", "scytheIron");
        registerScythe(scytheGold, "Gold Scythe", "scytheGold");
        registerScythe(scytheDiamond, "Diamond Scythe", "scytheDiamond");
        
        GameRegistry.registerItem(upgradeEffeciency, "upgradeEffeciency");
        GameRegistry.registerItem(upgradeNight, "upgradeNight");
        GameRegistry.registerItem(upgradeSpeed, "upgradeSpeed");
        GameRegistry.registerItem(upgradeAutomation, "upgradeAutomation");

        GameRegistry.registerItem(universalSeed, "universalSeed");
        GameRegistry.registerItem(seed, "seed");
        GameRegistry.registerItem(shard, "shard");
    }

    private static void registerNBT() {

    }

    private static void registerScythe(Item item, String name, String key) {
        item.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":" + key).setUnlocalizedName(key);
        GameRegistry.registerItem(item, key);
    }

}
