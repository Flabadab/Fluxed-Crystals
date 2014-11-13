package fluxedCrops.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thermalfoundation.item.TFItems;
import tterrag.core.TTCore;
import tterrag.core.common.compat.CompatabilityRegistry;
import tterrag.core.common.util.RegisterTime;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.ModProps;
import fluxedCrops.api.CropBase;
import fluxedCrops.blocks.crops.BlockCrop;
import fluxedCrops.items.FCItems;
import fluxedCrops.tileEntity.TileEntityManagerBlock;
import fluxedCrops.tileEntity.TileEntityPowerBlock;
import fluxedCrops.tileEntity.TileEntitySeedInfuser;

public class FCBlocks {

	public static Block ironCrop = new BlockCrop();
	public static Block goldCrop = new BlockCrop();
	public static Block coalCrop = new BlockCrop();
	public static Block charcoalCrop = new BlockCrop();
	public static Block copperCrop = new BlockCrop();
	

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
		registerCrop(ironCrop, "Iron Crop", "Crop_Iron", (CreativeTabs) null, "iron", new ItemStack(FCItems.ironSeed), TFItems.nuggetIron);
		registerCrop(goldCrop, "Gold Crop", "Crop_Gold", (CreativeTabs) null, "gold", new ItemStack(FCItems.goldSeed), new ItemStack(Items.gold_nugget));
		registerCrop(coalCrop, "Coal Crop", "Crop_Coal", (CreativeTabs) null, "coal", new ItemStack(FCItems.coalSeed), TFItems.dustCoal);
		registerCrop(charcoalCrop, "Charcoal Crop", "Charcoal_Coal", (CreativeTabs) null, "charcoal", new ItemStack(FCItems.charcoalSeed), TFItems.dustCharcoal);
		registerCrop(copperCrop, "Copper Crop", "Copper_Coal", (CreativeTabs) null, "copper", new ItemStack(FCItems.copperSeed), TFItems.nuggetCopper);
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

	private static void registerCrop(Block block, String name, String key, CreativeTabs tab, String material, ItemStack seed, ItemStack drop) {
		((CropBase) block).setMaterial(material);
		((BlockCrop) block).setSeed(seed);
		((BlockCrop) block).setDrop(drop);
		block.setBlockName(name).setCreativeTab(tab);
		GameRegistry.registerBlock(block, key);
	}
}
