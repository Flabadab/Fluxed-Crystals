package fluxedCrops.client.render;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fluxedCrops.FluxedCrops;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.blocks.BlockSeedInfuser;
import fluxedCrops.blocks.crops.BlockCrop;
import fluxedCrops.tileEntity.TileEntityCrop;
import fluxedCrops.tileEntity.TileEntitySeedInfuser;

public class SeedInfuserRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

		int meta = world.getBlockMetadata(x, y, z);
		renderer.renderStandardBlock(block, x, y, z);
		if (meta == 1) {
			TileEntitySeedInfuser tile = (TileEntitySeedInfuser) world.getTileEntity(x, y, z);
			int color = RecipeRegistry.getColor(tile.getOutputNumber());
			Tessellator tess = Tessellator.instance;
			tess.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
			tess.setColorOpaque_I(color);
			renderer.setOverrideBlockTexture(((BlockSeedInfuser) block).textures[2]);
			renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, new Color(color).getRed(), new Color(color).getGreen(), new Color(color).getBlue());
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return FluxedCrops.seedInfuserRenderID;
	}
}
