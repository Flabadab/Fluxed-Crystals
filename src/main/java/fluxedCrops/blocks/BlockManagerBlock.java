package fluxedCrops.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import fluxedCrops.FluxedCrops;
import fluxedCrops.tileEntity.TileEntityManagerBlock;

/**
 * Created by Jared on 11/3/2014.
 */
public class BlockManagerBlock extends Block implements ITileEntityProvider{
    protected BlockManagerBlock() {
        super(Material.anvil);
        this.setHardness(2.0F);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float par7, float par8, float par9) {

		player.openGui(FluxedCrops.instance, 0, world, x, y, z);
		return true;
	}

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityManagerBlock();
    }
}
