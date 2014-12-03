package fluxedCrystals.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.ModProps;
import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.tileEntity.TileEntityManagerBlock;
import fluxedCrystals.tileEntity.TileEntityPowerBlock;
import fluxedCrystals.tileEntity.TileEntitySeedInfuser;

public class FCBlocks {

	public static Block crop = new BlockCrystal();
  

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
		GameRegistry.registerTileEntity(TileEntityCrystal.class, "crop");
    
	}

	private static void registerBlocks() {
	  GameRegistry.registerBlock(crop, "crop");
		registerBlock(powerBlock, "Power Block", "power_block");
		registerBlock(managerBlock, "Manager Block", "Manager_Block");
		registerBlock(seedInfuser, "Seed Infuser", "Seed_Infuser");
	}

	private static void registerBlock(Block block, String name, String key) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(FluxedCrystals.tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerBlock(Block block, String name, String key, CreativeTabs tab) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(tab);
		GameRegistry.registerBlock(block, key);
	}
}
