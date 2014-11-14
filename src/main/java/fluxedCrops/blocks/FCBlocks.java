package fluxedCrops.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.ModProps;
import fluxedCrops.api.CropBase;
import fluxedCrops.blocks.crops.BlockCharcoalCrop;
import fluxedCrops.blocks.crops.BlockCoalCrop;
import fluxedCrops.blocks.crops.BlockGoldCrop;
import fluxedCrops.blocks.crops.BlockIronCrop;
import fluxedCrops.tileEntity.TileEntityManagerBlock;
import fluxedCrops.tileEntity.TileEntityPowerBlock;
import fluxedCrops.tileEntity.TileEntitySeedInfuser;

public class FCBlocks {

	public static Block ironCrop = new BlockIronCrop();
	public static Block goldCrop = new BlockGoldCrop();
	public static Block coalCrop = new BlockCoalCrop();
	public static Block charcoalCrop = new BlockCharcoalCrop();

	public static Block powerBlock = new BlockPowerBlock();
	public static Block managerBlock = new BlockManagerBlock();
	public static Block seedInfuser = new BlockSeedInfuser();

	public static void init() {
		registerBlocks();
		registerTileEntity();

	}

	private static void registerTileEntity() {
		GameRegistry.registerTileEntity(TileEntityManagerBlock.class, "managerBlock");
		GameRegistry.registerTileEntity(TileEntityPowerBlock.class, "powerBlock");
		GameRegistry.registerTileEntity(TileEntitySeedInfuser.class, "seedInfuser");

	}

	private static void registerBlocks() {
		registerCrop(ironCrop, "Iron Crop", "Crop_Iron", (CreativeTabs) null, "iron");
		registerCrop(goldCrop, "Gold Crop", "Crop_Gold", (CreativeTabs) null, "gold");
		registerCrop(coalCrop, "Coal Crop", "Crop_Coal", (CreativeTabs) null, "coal");
		registerCrop(charcoalCrop, "Charcoal Crop", "Charcoal_Coal", (CreativeTabs) null, "charcoal");

		registerBlock(powerBlock, "Power Block", "power_block");
		registerBlock(managerBlock, "Manager Block", "Manager_Block");
		registerBlock(seedInfuser, "Seed Infuser", "Seed_Infuser");

	}

	private static void registerBlock(Block block, String name, String key) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerBlock(Block block, String name, String key, CreativeTabs tab) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerCrop(Block block, String name, String key, CreativeTabs tab, String material) {
		((CropBase) block).setMaterial(material);
		block.setBlockName(name).setCreativeTab(tab);
		GameRegistry.registerBlock(block, key);
	}
}
