package fluxedCrystals.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.ModProps;
import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.config.ConfigProps;
import fluxedCrystals.tileEntity.TileEntityBloodManager;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.tileEntity.TileEntityGlass;
import fluxedCrystals.tileEntity.TileEntityIndustrialManager;
import fluxedCrystals.tileEntity.TileEntityManaManager;
import fluxedCrystals.tileEntity.TileEntityManagerBlock;
import fluxedCrystals.tileEntity.TileEntityPowerBlock;
import fluxedCrystals.tileEntity.TileEntitySeedInfuser;
import fluxedCrystals.tileEntity.TileEntityThaumicManager;

public class FCBlocks {

	public static Block crop = new BlockCrystal();

	public static Block powerBlock = new BlockPowerBlock();
	public static Block managerBlock = new BlockManagerBlock();
	public static Block managerBlood = new BlockBloodManager();
	public static Block managerThaumic = new BlockThaumicManager();
	public static Block managerMana = new BlockManaManager();
	public static Block managerIndustrial = new BlockIndustrialManager();

	public static Block seedInfuser = new BlockSeedInfuser();
	public static Block infusedGlass = new BlockGlass();

	public static void init() {

		registerBlocks();
		registerTileEntity();

	}

	private static void registerTileEntity() {

		GameRegistry.registerTileEntity(TileEntityManagerBlock.class, "managerBlock");
		GameRegistry.registerTileEntity(TileEntityBloodManager.class, "bloodManager");
		GameRegistry.registerTileEntity(TileEntityThaumicManager.class, "thaumicManager");
		GameRegistry.registerTileEntity(TileEntityManaManager.class, "manaManager");
		GameRegistry.registerTileEntity(TileEntityIndustrialManager.class, "industrialManager");

		GameRegistry.registerTileEntity(TileEntityPowerBlock.class, "powerBlock");
		GameRegistry.registerTileEntity(TileEntitySeedInfuser.class, "seedInfuser");
		GameRegistry.registerTileEntity(TileEntityCrystal.class, "crop");
		GameRegistry.registerTileEntity(TileEntityGlass.class, "glass");

	}

	private static void registerBlocks() {
		GameRegistry.registerBlock(crop, "crop");
		registerBlock(powerBlock, "Power Block", "power_block");
		registerBlock(managerBlock, "Manager Block", "Manager_Block");
		registerBlock(managerBlood, "Blood Manager Block", "Manager_Block_Blood");
		registerBlock(managerThaumic, "Thaumic Manager Block", "Manager_Block_Thaumic");
		registerBlock(managerMana, "Mana Manager Block", "Manager_Block_Mana");
		registerBlock(managerIndustrial, "Industrial Manager Block", "Manager_Block_Industrial");

		registerBlock(seedInfuser, "Seed Infuser", "Seed_Infuser");
		GameRegistry.registerBlock(infusedGlass, "infusedGlass");
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
