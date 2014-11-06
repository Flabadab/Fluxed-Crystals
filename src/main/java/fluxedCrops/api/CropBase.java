package fluxedCrops.api;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fluxedCrops.blocks.FCBlocks;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Random;

public abstract class CropBase extends BlockCrops {


    public boolean growCrop(World world, int x, int y, int z, Random rand) {
        if (world.getBlockLightValue(x, y + 1, z) >= 9) {
            int l = world.getBlockMetadata(x, y, z);

            if (l < 7) {
                float f = this.func_149864_n(world, x, y, z);

                if (rand.nextInt((int) (25.0F / f) + 1) == 0) {
                    ++l;
                    world.setBlockMetadataWithNotify(x, y, z, l, 2);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand) {

    }

    private float func_149864_n(World world, int x, int y, int z) {
        float f = 1.0F;

        for (int l = x - 1; l <= x + 1; ++l) {
            for (int i1 = z - 1; i1 <= z + 1; ++i1) {
                float f1 = 0.0F;

                if (world.getBlock(l, y - 1, i1).canSustainPlant(world, l, y - 1, i1, ForgeDirection.UP, this)) {
                    f1 = 2.0F;
                }

                f += f1;
            }
        }


        return f;
    }

    /**
     * Checks to see if its valid to put this block at the specified
     * coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return world.getBlock(x, y, z) == FCBlocks.powerBlock;
    }

    public int getRenderType() {
        return 6;
    }

    protected abstract Item getSeed();



    protected abstract Item getDrop();

    public Item getItemDropped(int meta, Random random, int p_149650_3_) {
        return getSeed();
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random) {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return this.getSeed();
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z,
                                         int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this.getSeed()));
        ret.add(new ItemStack(this.getDrop(), 1, 0));

        return ret;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified
     * items
     */
    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_,
                                          int p_149690_3_, int p_149690_4_, int p_149690_5_,
                                          float p_149690_6_, int p_149690_7_) {
        super.dropBlockAsItemWithChance(p_149690_1_, p_149690_2_, p_149690_3_,
                p_149690_4_, p_149690_5_, p_149690_6_, 0);
    }

    // TODO remove comment
    // public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int
    // p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
    // return false;
    // }
    //
    // public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int
    // p_149852_3_, int p_149852_4_, int p_149852_5_) {
    // return false;
    // }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World world, int x, int y, int z) {
        if (world.getBlock(x, y - 1, z) == FCBlocks.powerBlock)
            return true;

        return false;
    }
}
