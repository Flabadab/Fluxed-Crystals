package fluxedCrystals.client.gui.manager;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fluxedCrystals.ModProps;
import fluxedCrystals.tileEntity.TileEntityManagerBlock;

public class GUIManagerBlock extends GuiContainer {

	private TileEntityManagerBlock tile;

	public GUIManagerBlock(InventoryPlayer invPlayer, TileEntityManagerBlock tile2) {
		super(new ContainerManagerBlock(invPlayer, tile2));

		xSize = 176;
		ySize = 166;
		this.tile = tile2;

	}

	private static final ResourceLocation texture = new ResourceLocation(ModProps.modid, "textures/gui/CustomManager.png");

	public void initGui() {
		super.initGui();

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

}
