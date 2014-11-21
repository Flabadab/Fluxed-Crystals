package fluxedCrops.client.render;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fluxedCrops.FluxedCrops;
import fluxedCrops.api.RecipeRegistry;
import fluxedCrops.blocks.crops.BlockCrop;
import fluxedCrops.proxy.ClientProxy;
import fluxedCrops.tileEntity.TileEntityCrop;

public class CropRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderer.renderBlockCrops(block, x, y, z);
		if (world.getBlockMetadata(x, y, z) >= 7) {
			Tessellator tess = Tessellator.instance;
			TileEntityCrop tile = (TileEntityCrop) world.getTileEntity(x, y, z);
			Color blockColor = new Color(RecipeRegistry.getColor(tile.getIndex()));
			tess.setColorOpaque(blockColor.getRed(), blockColor.getBlue(), blockColor.getGreen());
			renderer.setOverrideBlockTexture(((BlockCrop) block).getFlowerTexture());
			renderer.renderBlockCrops(block, x, y, z);
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
