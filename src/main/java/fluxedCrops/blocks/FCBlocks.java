package fluxedCrops.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrops.ModProps;
import fluxedCrops.blocks.crops.BlockCrop;
import fluxedCrops.tileEntity.TileEntityCrop;
import fluxedCrops.tileEntity.TileEntityManagerBlock;
import fluxedCrops.tileEntity.TileEntityPowerBlock;
import fluxedCrops.tileEntity.TileEntitySeedInfuser;

public class FCBlocks {

	public static Block crop = new BlockCrop();
//	public static Block cropIron = new BlockCrop();
//	public static Block cropGold = new BlockCrop();
//	public static Block cropCoal = new BlockCrop();
//	public static Block cropCharcoal = new BlockCrop();
//	public static Block cropRedstone = new BlockCrop();
//	public static Block cropDiamond = new BlockCrop();
//
//	public static Block cropCopper = new BlockCrop();
//
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
		GameRegistry.registerTileEntity(TileEntityCrop.class, "crop");
	}

	private static void registerBlocks() {
		GameRegistry.registerBlock(crop, "crop");
//		registerCrop(cropIron, "Iron Crop", "Crop_Iron", (CreativeTabs) null, "iron", new ItemStack(FCItems.seedIron), TFItems.nuggetIron);
//		registerCrop(cropGold, "Gold Crop", "Crop_Gold", (CreativeTabs) null, "gold", new ItemStack(FCItems.seedGold), new ItemStack(Items.gold_nugget));
//		registerCrop(cropCoal, "Coal Crop", "Crop_Coal", (CreativeTabs) null, "coal", new ItemStack(FCItems.seedCoal), TFItems.dustCoal);
//		registerCrop(cropCharcoal, "Charcoal Crop", "Crop_Charcoal", (CreativeTabs) null, "charcoal", new ItemStack(FCItems.seedCharcoal), TFItems.dustCharcoal);
//		registerCrop(cropRedstone, "Redstone Crop", "Crop_Redstone", (CreativeTabs) null, "redstone", new ItemStack(FCItems.seedRedstone), new ItemStack(Items.redstone));
//		registerCrop(cropDiamond, "Diamond Crop", "Crop_Diamond", (CreativeTabs) null, "diamond", new ItemStack(FCItems.seedDiamond), new ItemStack(Items.diamond));
//
//		registerCrop(cropCopper, "Copper Crop", "Copper_Coal", (CreativeTabs) null, "copper", new ItemStack(FCItems.seedCopper), TFItems.nuggetCopper);
//
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
}
