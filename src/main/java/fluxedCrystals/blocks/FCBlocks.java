package fluxedCrystals.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.Loader;
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
import fluxedCrystals.tileEntity.TileEntityRoughChunk;
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
	public static Block roughChunk = new BlockRoughChunk();

	public static void init() {

		registerBlocks();
		registerTileEntity();

	}

	private static void registerTileEntity() {

		GameRegistry.registerTileEntity(TileEntityManagerBlock.class, "managerBlock");
		registerTileEntitys(TileEntityBloodManager.class, "bloodManager", "AWWayofTime", ConfigProps.bloodMagicAddon);
		registerTileEntitys(TileEntityThaumicManager.class, "thaumicManager", "Thaumcraft", ConfigProps.thaumcraftAddon);
		registerTileEntitys(TileEntityManaManager.class, "manaManager", "Botania", ConfigProps.botaniaAddon);
		registerTileEntitys(TileEntityIndustrialManager.class, "industrialManager", "IC2", ConfigProps.IndustrialCraftAddon);

		GameRegistry.registerTileEntity(TileEntityPowerBlock.class, "powerBlock");
		GameRegistry.registerTileEntity(TileEntitySeedInfuser.class, "seedInfuser");
		GameRegistry.registerTileEntity(TileEntityCrystal.class, "crop");
		GameRegistry.registerTileEntity(TileEntityGlass.class, "glass");
		GameRegistry.registerTileEntity(TileEntityRoughChunk.class, "roughChunk");

	}

	private static void registerBlocks() {
		GameRegistry.registerBlock(crop, "crop");
		registerBlock(powerBlock, "Power Block", "power_block");
		registerBlock(managerBlock, "Manager Block", "Manager_Block");
		registerBlock(managerBlood, "Blood Manager Block", "Manager_Block_Blood", "AWWayofTime", ConfigProps.bloodMagicAddon);
		registerBlock(managerThaumic, "Thaumic Manager Block", "Manager_Block_Thaumic", "Thaumcraft", ConfigProps.thaumcraftAddon);
		registerBlock(managerMana, "Mana Manager Block", "Manager_Block_Mana", "Botania", ConfigProps.botaniaAddon);
		registerBlock(managerIndustrial, "Industrial Manager Block", "Manager_Block_Industrial", "IC2", ConfigProps.IndustrialCraftAddon);

		registerBlock(seedInfuser, "Seed Infuser", "Seed_Infuser");
		
		GameRegistry.registerBlock(infusedGlass, "infusedGlass");
		GameRegistry.registerBlock(roughChunk, "roughChunkBlock");
		
	}

	private static void registerBlock(Block block, String name, String key) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(FluxedCrystals.tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerBlock(Block block, String name, String key, CreativeTabs tab) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerTileEntitys(Class<? extends TileEntity> tile, String name, String modidActive, boolean configActive) {
		if (Loader.isModLoaded(modidActive) && configActive)
			GameRegistry.registerTileEntity(tile, name);
	}

	private static void registerBlock(Block block, String name, String key, String modidActive, boolean configActive) {
		if (Loader.isModLoaded(modidActive) && configActive) {
			block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(FluxedCrystals.tab);
			GameRegistry.registerBlock(block, key);
		}
	}

}
