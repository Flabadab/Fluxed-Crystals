package fluxedCrops.items.seeds;

import fluxedCrops.api.SeedBase;
import fluxedCrops.blocks.FCBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

/**
 * Created by Jared on 11/2/2014.
 */
public class ItemIronSeed extends SeedBase{


    public ItemIronSeed() {
        super(FCBlocks.ironCrop);
    }

    @Override
    public Block getCrop() {
        return FCBlocks.ironCrop;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Crop;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return FCBlocks.ironCrop;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return 0;
    }
}
