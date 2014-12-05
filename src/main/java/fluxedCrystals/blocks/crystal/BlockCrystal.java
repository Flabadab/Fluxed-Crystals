package fluxedCrystals.blocks.crystal;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fluxedCrystals.api.CrystalBase;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.config.ConfigProps;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.items.ItemScythe;
import fluxedCrystals.tileEntity.TileEntityCrystal;

public class BlockCrystal extends CrystalBase implements ITileEntityProvider {
	public BlockCrystal() {
		setHardness(0.5F);
	}

	@Override
	public Item getItem(World world, int x, int y, int z) {
		return new ItemStack(FCItems.seed, 1, ((TileEntityCrystal) world.getTileEntity(x, y, z)).getIndex()).getItem();
	}

	public void dropCropDrops(World world, int x, int y, int z, int fortune) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
		if (world.getBlockMetadata(x, y, z) >= 7) {
			doDrop(crop, world, x, y, z, 0);
		}
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube() {
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock() {
		return false;
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		return null;
	}

	private void doDrop(TileEntityCrystal crop, World world, int x, int y, int z, int itemMultiplier) {
		ItemStack drop = crop.getDrop();

		if (drop == null) {
			return;
		}
		if (ConfigProps.shardDrop) {
			dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.shard, drop.stackSize + itemMultiplier, crop.getIndex()));
		} else {
			dropBlockAsItem(world, x, y, z, new ItemStack(drop.getItem(), drop.stackSize + itemMultiplier, drop.getItemDamage()));
		}
		if (RecipeRegistry.getWeightedDrop(crop.getIndex()) != null) {
			if (RecipeRegistry.getWeightedDropChance(crop.getIndex()) == world.rand.nextInt(9) + 1) {
				dropBlockAsItem(world, x, y, z, RecipeRegistry.getWeightedDrop(crop.getIndex()));
			}
		}
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
		dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.seed, 1, crop.getIndex()));
		ItemStack stack = player.getCurrentEquippedItem();
		if (stack != null && stack.getItem() instanceof ItemScythe) {
			if (stack.isItemEqual(new ItemStack(FCItems.scytheWood))) {
				if (world.rand.nextInt(4) == 0) {
					dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()));
				} else {
					dropCropDrops(world, x, y, z, 0);
				}
			}
			if (stack.isItemEqual(new ItemStack(FCItems.scytheStone))) {
				if (world.rand.nextInt(3) == 0) {
					dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()));
				} else {
					dropCropDrops(world, x, y, z, 0);
				}
			}
			if (stack.isItemEqual(new ItemStack(FCItems.scytheIron))) {
				if (world.rand.nextInt(2) == 0) {
					dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()));
				} else {
					dropCropDrops(world, x, y, z, 0);
				}
			}
			if (stack.isItemEqual(new ItemStack(FCItems.scytheGold))) {
				if (world.rand.nextInt(1) == 0) {
					dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()));
				} else {
					dropCropDrops(world, x, y, z, 0);
				}
			}
			if (stack.isItemEqual(new ItemStack(FCItems.scytheDiamond))) {
				dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()));
			} else {
				dropCropDrops(world, x, y, z, 0);
			}
		} else {
			dropCropDrops(world, x, y, z, 0);
		}
		if (RecipeRegistry.getWeightedDrop(crop.getIndex()) != null) {
			if (RecipeRegistry.getWeightedDropChance(crop.getIndex()) == world.rand.nextInt(9) + 1) {
				dropBlockAsItem(world, x, y, z, RecipeRegistry.getWeightedDrop(crop.getIndex()));
			}
		}
		world.setBlock(x, y, z, Blocks.air);

	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
		if (world.getBlockMetadata(x, y, z) >= 7) {
			ItemStack stack = player.getCurrentEquippedItem();
			if (stack != null && stack.getItem() instanceof ItemScythe) {
				if (stack.isItemEqual(new ItemStack(FCItems.scytheWood))) {
					if (world.rand.nextInt(4) == 0) {
						dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()));
					}
				}
				if (stack.isItemEqual(new ItemStack(FCItems.scytheStone))) {
					if (world.rand.nextInt(3) == 0) {
						dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()));
					}
				}
				if (stack.isItemEqual(new ItemStack(FCItems.scytheIron))) {
					if (world.rand.nextInt(2) == 0) {
						dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()));
					}
				}
				if (stack.isItemEqual(new ItemStack(FCItems.scytheGold))) {
					if (world.rand.nextInt(1) == 0) {
						dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()));
					}
				}
				if (stack.isItemEqual(new ItemStack(FCItems.scytheDiamond))) {
					dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()));
				}
			} else {
				dropCropDrops(world, x, y, z, 0);
			}
			world.setBlockMetadataWithNotify(x, y, z, 0, 3);
			return true;
		}
		return false;
	}

	public void setData(ItemStack seed, IBlockAccess world, int x, int y, int z) {
		TileEntityCrystal tile = (TileEntityCrystal) world.getTileEntity(x, y + 1, z);
		if (tile != null) {
			tile.init(seed.getItemDamage());
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCrystal();
	}
}
