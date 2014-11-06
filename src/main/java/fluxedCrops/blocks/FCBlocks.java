package fluxedCrops.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.ModProps;
import fluxedCrops.blocks.crops.BlockIronCrop;
import fluxedCrops.items.FCItems;
import fluxedCrops.tileEntity.TileEntityManagerBlock;
import fluxedCrops.tileEntity.TileEntityPowerBlock;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class FCBlocks {

    public static Block ironCrop = new BlockIronCrop();
    public static Block powerBlock = new BlockPowerBlock();
    public static Block managerBlock = new BlockManagerBlock();


    public static void init() {
        registerBlocks();
        registerTileEntity();
    }

    private static void registerTileEntity() {
        GameRegistry.registerTileEntity(TileEntityManagerBlock.class, "managerBlock");
        GameRegistry.registerTileEntity(TileEntityPowerBlock.class, "powerBlock");


    }

    private static void registerBlocks() {
        registerBlock(ironCrop, "Iron Crop", "Crop_Iron", (CreativeTabs) null);
        registerBlock(powerBlock, "Power Block", "Power_Block");
        registerBlock(managerBlock, "Manager Block", "Manager_Block");
    }

    private static void registerBlock(Block block, String name, String key) {
        block.setBlockName(name)
                .setBlockTextureName(ModProps.modid + ":" + key)
                .setCreativeTab(CreativeTabs.tabBlock);
        GameRegistry.registerBlock(block, key);
    }

    private static void registerBlock(Block block, String name, String key,
                                      CreativeTabs tab) {
        block.setBlockName(name)
                .setBlockTextureName(ModProps.modid + ":" + key)
                .setCreativeTab(tab);
        GameRegistry.registerBlock(block, key);
    }
}
