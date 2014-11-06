package fluxedCrops.blocks;

import fluxedCrops.tileEntity.TileEntitySeedInfuser;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Jared on 11/2/2014.
 */
public class BlockSeedInfuser extends Block implements ITileEntityProvider {

    public BlockSeedInfuser() {
        super(Material.anvil);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySeedInfuser();
    }
}
