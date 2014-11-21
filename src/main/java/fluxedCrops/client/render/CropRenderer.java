package fluxedCrops.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fluxedCrops.FluxedCrops;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.blocks.crops.BlockCrop;
import fluxedCrops.tileEntity.TileEntityCrop;

public class CropRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		renderer.renderCrossedSquares(block, x, y, z);
		if (meta >= 7) {
			TileEntityCrop tile = (TileEntityCrop) world.getTileEntity(x, y, z);
			int color = RecipeRegistry.getColor(tile.getIndex());
			Tessellator tess = Tessellator.instance;
	        tess.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
			tess.setColorOpaque_I(color);
			renderer.drawCrossedSquares(((BlockCrop)block).getFlowerTexture(), x, y, z, 1.0f);
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return FluxedCrops.cropRenderID;
	}
}
